package deu.team.jsp.admin.managelab;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.alert.AlertLastUserDto;
import deu.team.jsp.book.BookRepository;
import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.book.domain.Book;
import lombok.RequiredArgsConstructor;
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
        }
    }

    public void cancelBook(Long id) {
        Optional<Book> target = bookRepository.findById(id);
        if (target.isPresent()) {
            String targetStudentId = target.get().getStudentId();
            accountRepository.updateBookStatus(targetStudentId, 0);
            target.get().setApproveStatus(ApproveStatus.REJECT);
            bookRepository.save(target.get());
        }
    }

    public List<AlertLastUserDto> notifyLastStudent() {

        List<AlertLastUserDto> targetLastStudentList = new ArrayList<>();

        String[] labNoList = {"911", "915", "916", "918"};
        for (String targetLabNo : labNoList) {
            List<Book> bookList = bookRepository.getLastBookListByLabNo(targetLabNo);
            if (!bookList.isEmpty()) {
                Stream<Book> todayBookList = bookList.stream().filter(target -> target.getStartTime().toLocalDate().equals(LocalDate.now()));
                if (todayBookList.findFirst().isPresent()) {
                    String targetStudentId = todayBookList.max(Comparator.comparing(Book::getEndTime)).get().getStudentId();
                    targetLastStudentList.add(AlertLastUserDto.builder().labNo(targetLabNo).studentId(targetStudentId).build());
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
                accountRepository.getAccountByStudentId(targetAlertUser.getStudentId()).ifPresent(target -> {
                    String StudentName = target.getUserName();
                    if (targetAlertUser.getStudentId().equals(account.getStudentId())) {
                        model.addAttribute("AlertMsg" + targetAlertUser.getLabNo(), "[" + targetAlertUser.getLabNo() + " 강의실] '" + StudentName + "'학생은 현재 마지막 사용자입니다\n 실습실 마지막 사용에 대한 뒷정리 책임이 부여됩니다.");
                    } else {
                        model.addAttribute("AlertMsg" + targetAlertUser.getLabNo(), "[" + targetAlertUser.getLabNo() + " 강의실] 현재 마지막 사용자는 [" + StudentName + "]학생 입니다.");
                    }
                });

            }
        }
    }
}
