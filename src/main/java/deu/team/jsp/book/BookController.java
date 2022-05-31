package deu.team.jsp.book;

import deu.team.jsp.admin.managelab.ManageLabService;
import deu.team.jsp.alert.AlertLastUser;
import deu.team.jsp.interceptor.CheckSession;
import deu.team.jsp.nowBookStatus.NowBookStatusController;
import deu.team.jsp.nowBookStatus.NowBookStatusService;
import deu.team.jsp.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    BookService bookService;

    private final ScheduleService scheduleService;

    private final NowBookStatusService nowBookStatusService;


    @CheckSession
    @AlertLastUser
    @GetMapping("/bookPage")
    public String bookPage(HttpSession session, Model model, HttpServletRequest request){
        model.addAttribute("seats", nowBookStatusService.nowBookStatus(request,model));
        model.addAttribute("seats", nowBookStatusService.nowBookStatus(request,model));

//        manageLabService.alertUser(session, model);
        return "/WEB-INF/book/bookPage.jsp";

    }
    @CheckSession
    @AlertLastUser
    @GetMapping("/myBookStatusPage")
    public String myBookStatusPage(HttpSession session,Model model, HttpServletRequest request){
        model.addAttribute("myBookList",bookService.getMyBookList(request));
        return "/WEB-INF/student/myBookStatus.jsp";
    }

    @PostMapping("/seat")
    public String Seat(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("seats",bookService.checkSeat(request,response,model));
        return "/WEB-INF/book/seat.jsp";
    }

    @PostMapping("/book")
    public String Book(HttpServletRequest request,HttpServletResponse response) throws IOException {
        bookService.book(request,response);
        return "/WEB-INF/student/studentMain.jsp";
    }

    @RequestMapping(value = "/control" ,method = {RequestMethod.POST,RequestMethod.GET})
    public String removeBook(HttpServletRequest request){
        String remove = request.getParameter("remove");
        if(Objects.isNull(remove)){ //취소
            Integer cancel = Integer.valueOf(request.getParameter("cancel"));
            Long cancelId=cancel.longValue();
            bookService.removeBook(cancelId); //삭제
            bookService.changeAccountBookStatus(request); //사용자 예약 상태 변경
        }
        else{ //삭제
            Integer removeId = Integer.valueOf(request.getParameter("remove"));
            Long bookId=removeId.longValue();
            bookService.removeBook(bookId); //삭제
        }

        return "/WEB-INF/student/studentMain.jsp";
    }

    @GetMapping("/searchSchedule")
    public String searchSchedulePage(Model model){
        if (scheduleService.getScheduleCnt() > 0 ) {
            model.addAttribute("scheduleTimeList", scheduleService.getSubjectTime());
        }
        return "/WEB-INF/student/searchSchedule.jsp";

    }


}
