package deu.team.jsp.notification;

import deu.team.jsp.account.domain.Account;
import deu.team.jsp.notification.entity.Notification;
import deu.team.jsp.notification.entity.NotificationMessage;
import deu.team.jsp.notification.util.Util;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class NotificationService {

  @Autowired
  NotificationRepository notificationRepository;
  @Autowired
  NotificationMessageRepository notificationMessageRepository;

  public void addNotification(String accountId, String content) throws NoSuchAlgorithmException {

    //공지 엔티티 생성
    Notification notification = new Notification();
    notification.setAccountId(accountId);
    notification.setCreatedTime(LocalDateTime.now());
    notification.setIsConfirm(false);

    //공지 메시지 중복 검사
    String hash = Util.stringToSha256HexString(content);
    List<NotificationMessage> notificationMessages = notificationMessageRepository.findAll();
    Iterator<NotificationMessage> messageIterator = notificationMessages.iterator();
    while (messageIterator.hasNext()) {
      NotificationMessage notificationMessage = messageIterator.next();
      if (hash.equals(notificationMessage.getHash())) {
        //중복되면 같은 해시값을 가진다, 중복되는 메세지의 외래키를 삽입한다.
        notification.setMessage(notificationMessage);
        notificationRepository.save(notification);
        return;
      }
    }

    //중복되는 해시값이 없을 때 메시지 생성
    NotificationMessage notificationMessage = new NotificationMessage();
    notificationMessage.setHash(hash);
    notificationMessage.setContent(content);

    //메시지를 왜래키로 설정
    notification.setMessage(notificationMessage);

    notificationRepository.save(notification);
    return;
  }

  public void setMessageConfirm(String accountId) {
    notificationRepository.updateConfirmToTrue(accountId);
    return;
  }

  public int getUnConfirmMessageCount(String accountId) {
    return notificationRepository.findIdes(accountId).size();
  }
}
