package deu.team.jsp.admin;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
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
        if (oneTimeKeyService.checkOneTimeKey()) {
            String key = oneTimeKeyService.generateOneTimeKey();
            response.setContentType("text/html; charset=UTF-8");
            request.getSession().setAttribute("keyMsg", "일회용 토큰 값이 성공적으로 변경되었습니다.");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('일회용 토큰 값이 변경되었습니다. 메인 페이지로 이동합니다.'); location.href='/administrator';</script>");
//            out.flush();


        } else {
            String key = oneTimeKeyService.generateOneTimeKey();
            response.setContentType("text/html; charset=UTF-8");
            request.getSession().setAttribute("keyMsg", "일회용 토큰을 성공적으로 생성하였습니다.");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('정상적으로 실행되었습니다. 메인 페이지로 이동합니다.'); location.href='/administrator';</script>");
//            out.flush();
        }
        request.setCharacterEncoding("utf-8");
        request.getSession().setAttribute("key", oneTimeKeyService.getOneTimeKey());
        response.sendRedirect("/administrator");
//        RequestDispatcher rd = request.getRequestDispatcher("/administrator");
//        rd.forward(request, response);
    }
}
