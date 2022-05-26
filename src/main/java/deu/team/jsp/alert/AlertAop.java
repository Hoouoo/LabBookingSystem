package deu.team.jsp.alert;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.AccountService;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.admin.managelab.ManageLabService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
@RequiredArgsConstructor
public class AlertAop {

    private final ManageLabService manageLabService;

    @Around(value = "@annotation(AlertLastUser) && args(session, model)", argNames = "pjp,session,model")
    public Object alertLastUser(ProceedingJoinPoint pjp, HttpSession session, Model model) throws Throwable {
//        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        manageLabService.alertUser(session, model);
        return pjp.proceed(new Object[]{session, model});
    }

    @Around(value = "@annotation(AlertLastUser) && args(session, model, request)", argNames = "pjp,session,model, request")
    public Object alertUserName2(ProceedingJoinPoint pjp, HttpSession session, Model model, HttpServletRequest request) throws Throwable{
        manageLabService.alertUser(session, model);
        return pjp.proceed(new Object[]{session, model,request});
    }
}
