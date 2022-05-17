package deu.team.jsp.account;

import deu.team.jsp.account.domain.Role;
import deu.team.jsp.interceptor.CheckSession;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String LoginPage(){
        return "/WEB-INF/account/login.jsp";
    }

    @GetMapping("/signUpPage")
    public String SignUpPage(){
        return "/WEB-INF/account/signUp.jsp";
    }

    @PostMapping("/login")
    public String MainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role= accountService.Login(request, response);

        if (role.equals(Role.STUDENT)){
            return "WEB-INF/student/studentMain.jsp";
        }
        else if(role.equals(Role.ADMIN)){
            return "redirect:/manager";
        }
        else{
            return "WEB-INF/manager/professor.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session);
        session.invalidate();
        System.out.println(session);
        return "redirect:/";
    }

    @PostMapping("/signUp")
    public String SignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.SignUp(request,response);
        return "redirect:/";
    }

    @CheckSession
    @RequestMapping(value = "/adminAccountModifyPage",method = {RequestMethod.POST,RequestMethod.GET})
    public String AdminAccountModifyPage(){
        return "WEB-INF/manager/adminModifyAccount.jsp";
    }

    @CheckSession
    @PostMapping("/accountSearch")
    public String AccountSearch(HttpServletRequest request,HttpServletResponse response,
                                Model model) throws IOException {
        model.addAttribute("findAccount",accountService.AccountSearch(request,response,model));
        return "/adminAccountModifyPage";
    }

    @CheckSession
    @PostMapping("/adminAccountModify")
    public String AdminModify(HttpServletRequest request){
        accountService.modify(request);
        return "redirect:/";
    }

    @CheckSession
    @RequestMapping(value = "/studentAccountModifyPage",method = {RequestMethod.POST,RequestMethod.GET})
    public String StudentAccountModifyPage(){
        return "WEB-INF/student/studentModifyAccount.jsp";
    }

    @CheckSession
    @PostMapping("/studentAccountModify")
    public String StudentModify(HttpServletRequest request){
        accountService.modify(request);
        return "redirect:/";
    }


}
