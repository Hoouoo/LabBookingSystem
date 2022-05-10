package deu.team.jsp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String LoginPage(){
        return "/WEB-INF/Account/login.jsp";
    }

    @GetMapping("/signUpPage")
    public String SignUpPage(){
        return "/WEB-INF/Account/signUp.jsp";
    }

    @PostMapping("/login")
    public String MainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.Login(request,response);

        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat("HH:mm");
        String date=df.format(now);

        int cutLineTime=17;
        int currentTime=Integer.valueOf(date.substring(0,2));

        HttpSession session=request.getSession();
        Object account = session.getAttribute("account");
        if(Objects.isNull(account)){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('세션이 만료되었습니다. 메인 페이지로 이동합니다.'); location.href='/';</script>");
            out.flush();
        }

        if(cutLineTime >currentTime){
            return "/WEB-INF/book/before17.jsp";
        }

        return "/WEB-INF/book/after17.jsp";
    }

    @PostMapping("/signUp")
    public String SignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.SignUp(request,response);
        return "redirect:/";
    }


}
