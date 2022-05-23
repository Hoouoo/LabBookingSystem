package deu.team.jsp.bookExtend;

import deu.team.jsp.account.domain.Account;
import deu.team.jsp.alert.AlertService;
import deu.team.jsp.book.BookRepository;
import deu.team.jsp.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookExtendService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AlertService alertService;


    public LocalDateTime CheckExtend(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession httpSession = request.getSession();
        Account account = (Account) httpSession.getAttribute("account");

        LocalDateTime now=LocalDateTime.now();

        List<Book> lastBookList = bookRepository.getLastBookList(account.getStudentId());

        if(lastBookList.size()>=1){
            System.out.println(1);
            Book book = lastBookList.get(lastBookList.size() - 1);
            System.out.println("book = " + book);
            if(book.getEndTime().getDayOfMonth()==now.getDayOfMonth()){ //오늘 예약한 좌석이 있으면

                httpSession.setAttribute("myBook",book);
                model.addAttribute("myFinishTime",book.getEndTime()); //나의 예약 끝나는 시간

                List<Book> bookByX_y_labNo = bookRepository.getBookByX_Y_labNo(book.getSeatX(), book.getSeatY(), book.getLabNo(),now);
                if(bookByX_y_labNo.size()==0){//뒷 사람 예약이 없다.
                    System.out.println(2.5);
                    return null;
                }else{
                    System.out.println(3);
                    Book behindBook = bookByX_y_labNo.get(0); //나의 뒷사람 예약
                    return behindBook.getStartTime();
                }
            }else{
                System.out.println(4);
                System.out.println("예약한 좌석 없음");
                alertService.alertMessage("현재 예약한 좌석이 없습니다.", "/studentPage",response);
            }
        }
        else{
            System.out.println(5);
            alertService.alertMessage("현재 예약한 좌석이 없습니다.", "/studentPage",response);
        }
        return null;
    }

    public void extendSeat(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        String endTime = request.getParameter("endTime");

        int year = Integer.valueOf(endTime.substring(0, 4));
        int month = Integer.valueOf(endTime.substring(5, 7));

        int endDay = Integer.valueOf(endTime.substring(8, 10));

        int endHour = Integer.valueOf(endTime.substring(11, 13));
        int endMinute = Integer.valueOf(endTime.substring(14, 16));

        LocalDateTime bookEndTime= LocalDateTime.of(year,month,endDay,endHour,endMinute);

        HttpSession httpSession = request.getSession();
        Book myBook = (Book) httpSession.getAttribute("myBook");
        bookRepository.extendEndTime(bookEndTime,myBook.getStudentId());
        alertService.alertMessage("연장 완료 되었습니다.", "/studentPage", response);

    }
}
