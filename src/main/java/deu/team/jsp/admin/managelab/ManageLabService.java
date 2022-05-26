package deu.team.jsp.admin.managelab;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.book.BookRepository;
import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.book.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ManageLabService {

    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;

    public List<Book>getAllBookList(){
        return bookRepository.findAll();
    }
    public List<Book>getAllApproveList(){
        return bookRepository.getStatusList(ApproveStatus.APPROVE);
    }
    public List<Book>getAllRejectList(){
        return bookRepository.getStatusList(ApproveStatus.REJECT);
    }

    public void approveBook(Long id){
        Optional<Book> target = bookRepository.findById(id);
        if (target.isPresent()) {
            String targetStudentId = target.get().getStudentId();
            accountRepository.updateBookStatus(targetStudentId, 1);
            target.get().setApproveStatus(ApproveStatus.APPROVE);
            bookRepository.save(target.get());
        }
    }

    public void cancelBook(Long id){
        Optional<Book> target = bookRepository.findById(id);
        if (target.isPresent()) {
            String targetStudentId = target.get().getStudentId();
            accountRepository.updateBookStatus(targetStudentId, 0);
            target.get().setApproveStatus(ApproveStatus.REJECT);
            bookRepository.save(target.get());
        }
    }

    public String notifyLastStudent(){

        List<Book> bookList = bookRepository.findAll();

        Book lastBook = bookList.stream()
                .filter(target -> target.getEndTime().toLocalDate().equals(LocalDate.now()))
                .max(Comparator.comparing(Book::getEndTime)).orElse(null);

        if (Objects.isNull(lastBook)) {
            return null;
        }

        return lastBook.getStudentId();
    }
}
