package deu.team.jsp.bookExtend;

import deu.team.jsp.admin.managelab.ManageLabService;
import deu.team.jsp.alert.AlertLastUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class BookExtendController {

    @Autowired
    BookExtendService bookExtendService;

    @AlertLastUser
    @GetMapping("/bookExtendPage")
    public String BookExtendPage(HttpSession session, Model model ){
        return "/WEB-INF/bookExtend/bookExtendPage.jsp";
    }


    @GetMapping("/checkExtend")
    public String CheckExtend(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("finishTime", bookExtendService.CheckExtend(request,response,model));
        return "/bookExtendPage";
    }

    @PostMapping("/extendSeat")
    public String extendSeat(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {

        bookExtendService.extendSeat(request,response,model);

        return "/studentPage";
    }


}
