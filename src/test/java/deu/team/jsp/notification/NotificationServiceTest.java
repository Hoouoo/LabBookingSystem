package deu.team.jsp.notification;

import deu.team.jsp.notification.entity.Notification;
import deu.team.jsp.notification.entity.NotificationMessage;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
public class NotificationServiceTest {

  @Autowired
  NotificationService service;

  @Autowired
  NotificationRepository notificationRepository;

  @Autowired
  NotificationMessageRepository notificationMessageRepository;

  @Autowired
  NotificationService notificationService;


  @Test
  @Transactional
  public void notificationService() throws NoSuchAlgorithmException {

//    service.addNotification("20173207", "하이하이");
//    service.addNotification("20173207", "하이하이");
//    service.addNotification("20173208", "하이하이");
//    service.addNotification("20173207", "하이하이하이");
//    service.addNotification("20173207", "하이하이하이");

    List<Notification> notifications = notificationRepository.findAll();
    List<NotificationMessage> notificationMessages = notificationMessageRepository.findAll();

//    System.out.println(notifications);
//    System.out.println(notificationMessages);
//
//    LocalDateTime localDateTime = LocalDateTime.now();
//
//    System.out.println("year:" + localDateTime.getYear());
//    System.out.println("month: " + localDateTime.getMonthValue());
//    System.out.println("day: " + localDateTime.getDayOfMonth());
//    System.out.println("hour: " + localDateTime.getHour());
//    System.out.println("minute: " + localDateTime.getMinute());

  }

}
