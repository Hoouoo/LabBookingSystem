package deu.team.jsp.admin.managelab;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.alert.AlertLastUserDto;
import deu.team.jsp.book.BookRepository;
import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.book.domain.Book;
import deu.team.jsp.notification.NotificationService;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ManageLabService {

  private final BookRepository bookRepository;
  private final AccountRepository accountRepository;

  @Autowired
  private final NotificationService notificationService;

  public List<Book> getAllBookList() {
    return bookRepository.findAll();
  }

  public List<Book> getAllApproveList() {
    return bookRepository.getStatusList(ApproveStatus.APPROVE);
  }

  public List<Book> getAllRejectList() {
    return bookRepository.getStatusList(ApproveStatus.REJECT);
  }

  public void approveBook(Long id) {
    Optional<Book> target = bookRepository.findById(id);
    if (target.isPresent()) {
      String targetStudentId = target.get().getStudentId();
      accountRepository.updateBookStatus(targetStudentId, 1);
      target.get().setApproveStatus(ApproveStatus.APPROVE);
      bookRepository.save(target.get());

      //승인 메시지 사용자에게 전송
      try {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(targetStudentId).append("님의 예약 요청이 운영자로부터 승인 되었습니다.");
        String content = stringBuilder.toString();
        notificationService.addNotification(targetStudentId, content);
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void cancelBook(Long id) {
    Optional<Book> target = bookRepository.findById(id);
    if (target.isPresent()) {
      String targetStudentId = target.get().getStudentId();
      accountRepository.updateBookStatus(targetStudentId, 0);
      target.get().setApproveStatus(ApproveStatus.REJECT);
      bookRepository.save(target.get());

      //승인 거절 메시지 사용자에게 전송
      try {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(targetStudentId).append("님의 예약 요청이 운영자로부터 거절 되었습니다.");
        String content = stringBuilder.toString();
        notificationService.addNotification(targetStudentId, content);
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public List<AlertLastUserDto> notifyLastStudent() {

    List<AlertLastUserDto> targetLastStudentList = new ArrayList<>();

    String[] labNoList = {"911", "915", "916", "918"};
    for (String targetLabNo : labNoList) {
      List<Book> bookList = bookRepository.getLastBookListByLabNo(targetLabNo);
      if (!bookList.isEmpty()) {
        Stream<Book> todayBookList = bookList.stream()
            .filter(target -> target.getEndTime().toLocalDate().equals(LocalDate.now()));
        if (todayBookList.findFirst().isPresent()) {
          String targetStudentId = bookList.stream()
              .filter(target -> target.getEndTime().toLocalDate().equals(LocalDate.now()))
              .max(Comparator.comparing(Book::getEndTime)).get().getStudentId();
          targetLastStudentList.add(
              AlertLastUserDto.builder().labNo(targetLabNo).studentId(targetStudentId).build());
        }
      }
//                String targetStudentId = bookList.stream().filter(target -> target.getEndTime().toLocalDate().equals(LocalDate.now()))
//                        .max(Comparator.comparing(Book::getEndTime)).get().getStudentId();

    }

//        Book lastBook = bookList.stream()
//                .filter(target -> target.getEndTime().toLocalDate().equals(LocalDate.now()))
//                .max(Comparator.comparing(Book::getEndTime)).orElse(null);

    if (targetLastStudentList.isEmpty()) {
      return null;
    }

    return targetLastStudentList;
  }

  public void alertUser(HttpSession session, Model model) {
    List<AlertLastUserDto> studentIdListByLabNo = notifyLastStudent();
    Account account = (Account) session.getAttribute("account");

    if (Objects.nonNull(studentIdListByLabNo)) {
      for (AlertLastUserDto targetAlertUser : studentIdListByLabNo) {
        accountRepository.getAccountByStudentId(targetAlertUser.getStudentId())
            .ifPresent(target -> {
              String StudentName = target.getUserName();
              if (targetAlertUser.getStudentId().equals(account.getStudentId())) {
                model.addAttribute("AlertMsg" + targetAlertUser.getLabNo(),
                    "[" + targetAlertUser.getLabNo() + " 강의실] '" + StudentName
                        + "'학생은 현재 마지막 사용자입니다\n 실습실 마지막 사용에 대한 뒷정리 책임이 부여됩니다.");
              } else {
                model.addAttribute("AlertMsg" + targetAlertUser.getLabNo(),
                    "[" + targetAlertUser.getLabNo() + " 강의실] 현재 마지막 사용자는 [" + StudentName
                        + "]학생 입니다.");
              }
            });

      }
    }
  }

  /**
   * 리스트 목록에서 사라지게 삭제
   */
  public void deleteModifyUser(Long id) {
    bookRepository.findById(id).ifPresent(target -> {
      target.setApproveStatus(ApproveStatus.DELETE);
      bookRepository.save(target);
    });
  }

  // 일괄 처리를 위한 로직
  public void listAllApprove(Long[] id, ApproveStatus status) {
    List<Book> listTarget = new ArrayList<>();
    for (Long target : id) {
      bookRepository.findById(target).ifPresent(targetBook -> {
        targetBook.setApproveStatus(status);
        listTarget.add(targetBook);

      });
    }
    bookRepository.saveAll(listTarget);
  }
}

