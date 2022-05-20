package deu.team.jsp.admin;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.interceptor.CheckSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminMainController {

    private final OneTimeKeyService oneTimeKeyService;

    @CheckSession
//    @GetMapping("/manager")
////    @RequestMapping(value = "/manager",method = {RequestMethod.POST,RequestMethod.GET})
//    public String mainPage() {
//        return "/WEB-INF/manager/adminMain.jsp";
//    }

    @PostMapping("/onetimekey")
    public String generateKey(RedirectAttributes model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role targetRole;
        System.out.println("~~~> : " + request.getParameter("keyRole"));
        if (request.getParameter("keyRole").equals("STUDENT")) {
            targetRole = Role.STUDENT;
        } else {
            targetRole = Role.PROFESSOR;
        }
        if (oneTimeKeyService.checkOneTimeKey(targetRole)) {
            oneTimeKeyService.generateOneTimeKey(targetRole);
            response.setContentType("text/html; charset=UTF-8");
        } else {
             oneTimeKeyService.generateOneTimeKey(targetRole);
            response.setContentType("text/html; charset=UTF-8");
        }
        request.setCharacterEncoding("utf-8");
//        if (request.getParameter("keyRole").equals("STUDENT")) {
//            model.addFlashAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
//        } else {
//            model.addFlashAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));
//        }

//        request.getSession().setAttribute("keyMsg", "일회용 토큰 값이 성공적으로 생성되었습니다.");


//        response.sendRedirect(request.getRequestURL().toString());
        return "redirect:" + request.getHeader("Referer");
    }
}
