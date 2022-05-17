package deu.team.jsp.account;

import deu.team.jsp.OneTimeKey.OneTimeKey;
import deu.team.jsp.OneTimeKey.OneTimeKeyRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");
        String passKey = request.getParameter("passKey");

        String roleType=request.getParameter("roleType");
        Role role;
        if(roleType.equals("student")){
            role=Role.STUDENT;
        }else{
            role=Role.PROFESSOR;
        }

        Optional<OneTimeKey> key = oneTimeKeyRepository.findByRole(role);

        Account findByUserId = accountRepository.findByStudentId(studentId);

        if(Objects.isNull(findByUserId) && passKey.equals(key.get().getPassKey())){

                Account account=new Account(studentId,userName,userPassword,email,phoneNo,0,role);
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

    public Role Login(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            System.out.println(session);
        }
        return findByStudentId.getRole();

    }

    public Account AccountSearch(HttpServletRequest request, HttpServletResponse response,
                              Model model) throws IOException {
        String studentId = request.getParameter("studentId");
        Account findByStudentId = accountRepository.findByStudentId(studentId);

        if(Objects.isNull(findByStudentId)){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('해당 학번이 존재하지 않습니다.'); location.href='adminAccountModifyPage';</script>");
            out.flush();
            return null;
        }
        else{
            return findByStudentId;
        }
    }

    public void modify(HttpServletRequest request){

        String studentId = request.getParameter("studentId");
        String userName = request.getParameter("userName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");

        String modify = request.getParameter("modify");
        if(!Objects.isNull(modify)){ //삭제 버튼
            Account findByStudentId = accountRepository.findByStudentId(studentId);
            Role role=findByStudentId.getRole();
            int bookStatus=findByStudentId.getBookStatus();
            accountRepository.delete(findByStudentId);
            Account account=new Account(studentId,userName,userPassword,email,phoneNo,bookStatus,role);
            accountRepository.save(account);

        }
        else{
            Account findByStudentId = accountRepository.findByStudentId(studentId);
            accountRepository.delete(findByStudentId);
        }

    }

}
