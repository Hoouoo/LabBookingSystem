package deu.team.jsp.admin;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/administrator")
    public String mainPage() {
        return "/WEB-INF/admin/adminMain.jsp";
    }

    @PostMapping("/administrator")
    public void generateKey(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        if (request.getParameter("keyRole").equals("STUDENT")) {
            request.getSession().setAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
        } else {
            request.getSession().setAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));
        }
        request.getSession().setAttribute("keyMsg", "일회용 토큰 값이 성공적으로 생성되었습니다.");
        response.sendRedirect("/administrator");
    }
}
