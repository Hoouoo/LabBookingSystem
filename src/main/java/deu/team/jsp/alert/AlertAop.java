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
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
@RequiredArgsConstructor
public class AlertAop {

    private final ManageLabService manageLabService;
    private final AccountRepository accountRepository;

    @Around(value = "@annotation(AlertLastUser) && args(session, model)", argNames = "pjp,session,model")
    public Object alertLastUser(ProceedingJoinPoint pjp, HttpSession session, Model model) throws Throwable{
//        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String studentId = manageLabService.notifyLastStudent();
        Account account = (Account) session.getAttribute("account");

        //TODO 강의실 별로 알려줘야 함
        if (Objects.nonNull(studentId)) {
            String StudentName = accountRepository.findByUserName(studentId).get().getUserName();
            if (studentId.equals(account.getStudentId())){
                model.addAttribute("AlertMsg", "[관리자 메시지] '" + StudentName +"'학생은 현재 마지막 사용자입니다\n 실습실 마지막 사용에 대한 책임이 부여됩니다.");
            }else{
                model.addAttribute("AlertMsg", "[관리자 메시지] 현재 마지막 사용자는 [" + StudentName + "]학생 입니다.");
            }
        }
        return pjp.proceed(new Object[]{session, model});
    }

    @Around(value = "@annotation(AlertLastUser) && args(session, model, request)", argNames = "pjp,session,model, request")
    public Object alertUserName2(ProceedingJoinPoint pjp, HttpSession session, Model model, HttpServletRequest request) throws Throwable{
//        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String studentId = manageLabService.notifyLastStudent();
        Account account = (Account) session.getAttribute("account");

        //TODO 강의실 별로 알려줘야 함
        if (Objects.nonNull(studentId)) {
            String StudentName = accountRepository.findByUserName(studentId).get().getUserName();
            if (studentId.equals(account.getStudentId())){
                model.addAttribute("AlertMsg", "[관리자 메시지] '" + StudentName +"'학생은 현재 마지막 사용자입니다\n 실습실 마지막 사용에 대한 책임이 부여됩니다.");
            }else{
                model.addAttribute("AlertMsg", "[관리자 메시지] 현재 마지막 사용자는 [" + StudentName + "]학생 입니다.");
            }
        }
        return pjp.proceed(new Object[]{session, model,request});
    }
}
