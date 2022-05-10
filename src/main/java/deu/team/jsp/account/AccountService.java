package deu.team.jsp.account;

import deu.team.jsp.OneTimeKey.OneTimeKey;
import deu.team.jsp.OneTimeKey.OneTimeKeyRepository;
import deu.team.jsp.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OneTimeKeyRepository oneTimeKeyRepository;

    public void SignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String studentId = request.getParameter("studentId");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String passKey = request.getParameter("passKey");

        Optional<OneTimeKey> findById = oneTimeKeyRepository.findById(1L);


        Account findByUserId = accountRepository.findByStudentId(studentId);
        if(Objects.isNull(findByUserId) && passKey.equals(findById.get().getPassKey())){
            Account account=new Account(studentId,userName,userPassword);
            accountRepository.save(account);
        }
        else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('이미 존재하는 학번 이거나 인증키가 일치하지 않습니다." +
                    " 메인 페이지로 이동합니다.'); location.href='/';</script>");
            out.flush();
        }
    }

    public void Login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String studentId = request.getParameter("studentId");
        String userPassword = request.getParameter("userPassword");

        Account findByStudentId = accountRepository.findByStudentId(studentId);

        if(Objects.isNull(findByStudentId) || !userPassword.equals(findByStudentId.getUserPassword())){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('회원 정보가 일치하지 않습니다.'); location.href='/';</script>");
            out.flush();
        }else{
            HttpSession session=request.getSession();
            session.setAttribute("account", findByStudentId);
            System.out.println("session = " + session);
        }

    }

}
