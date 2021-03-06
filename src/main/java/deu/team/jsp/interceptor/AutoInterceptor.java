package deu.team.jsp.interceptor;

import deu.team.jsp.account.domain.Account;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class AutoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // HandlerMethod 타입인지 체크
        if( handler instanceof HandlerMethod == false ) {
            return true;
        }

        // handler를 HandlerMethod 타입으로 다운캐스팅 / 요청을 처리할 메소드
        HandlerMethod handlerMethod=(HandlerMethod) handler;

        // 메소드에 붙은 어노테이션 참조
        CheckSession checkSession=handlerMethod.getMethodAnnotation(CheckSession.class);

        if(checkSession == null) {
            // 메소드에 붙은 checkSession 어노테이션이 없다면 권한 제약이 없다는 것으로 생각하고 통과
            return true;
        }

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) { //세션 없음
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('세션이 없습니다. 메인 페이지로 이동합니다.'); location.href='/';</script>");
            out.flush();
            return false;
        }
        else{
            System.out.println("세션 있음");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
