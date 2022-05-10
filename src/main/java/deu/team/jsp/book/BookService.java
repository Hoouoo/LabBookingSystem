package deu.team.jsp.book;

import deu.team.jsp.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void book(HttpServletRequest request){

        HttpSession httpSession=request.getSession();
        Account account = (Account) httpSession.getAttribute("account");
        System.out.println("httpSession = " + account.getStudentId());

        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String seat = request.getParameter("seat");

        int year=Integer.valueOf(date.substring(0,4));
        int month=Integer.valueOf(date.substring(5,7));
        int day=Integer.valueOf(date.substring(8,10));

        int startHour=Integer.valueOf(startTime.substring(0,2));
        int endHour=Integer.valueOf(endTime.substring(0,2));

        int seatX=Integer.valueOf(seat.substring(0,1));
        int seatY=Integer.valueOf(seat.substring(2,3));

        LocalDateTime start= LocalDateTime.of(year, month, day, startHour, 0);
        LocalDateTime end= LocalDateTime.of(year, month, day, endHour, 0);

        Book book=new Book(account.getStudentId(),start,end, 919, seatX, seatY);

        bookRepository.save(book);
    }

}
