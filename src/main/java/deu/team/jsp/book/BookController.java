package deu.team.jsp.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/seat")
    public String Seat(){
        return "/WEB-INF/book/seat.jsp";
    }

    @PostMapping("/book")
    public String Book(HttpServletRequest request){

        bookService.book(request);
        return "redirect:/";
    }

}
