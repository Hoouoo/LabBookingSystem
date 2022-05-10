package deu.team.jsp.book;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class BookStatus {

    private int seat[][]={};

    private int roomNo;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Book> books;

    @Autowired
    BookRepository bookRepository;

    public void checkSeat(){
        books = bookRepository.bookList(roomNo, startTime, endTime);
        System.out.println("asdfas");
    }

}
