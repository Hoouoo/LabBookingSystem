package deu.team.jsp.admin.managelab;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;

import deu.team.jsp.notification.NotificationService;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;

import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.interceptor.CheckSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ManageLabController {

  private final ManageLabService manageLabService;
  private final OneTimeKeyService oneTimeKeyService;


  @Autowired
  private final NotificationService notificationService;


  @GetMapping("/admin/managelab")
  public String manageLabMainPage(Model model) {
    model.addAttribute("bookList", manageLabService.getAllBookList());
    model.addAttribute("bookApproveList", manageLabService.getAllApproveList());
    model.addAttribute("bookRejectList", manageLabService.getAllRejectList());
    model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
    model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));

    return "/WEB-INF/manager/manageLab.jsp";
  }

  @PostMapping("/admin/managelab")
  public String manageLab(HttpServletRequest request, HttpServletResponse response, Model model)
      throws IOException {
    String msg = null;
    if (Objects.nonNull(request.getParameter("approve"))) {
      manageLabService.approveBook(Long.parseLong(request.getParameter("approve")));
      msg = "승인되었습니다.";
    } else if (Objects.nonNull(request.getParameter("cancel"))) {
      // TODO 사용자 거절 메시지 저장 서비스 로직 구현


      try {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(account.getStudentId()).append("님의 예약 요청이 운영자로부터 거절 되었습니다.");
        String content = stringBuilder.toString();
        notificationService.addNotification(account.getStudentId(), content);
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }

      manageLabService.cancelBook(Long.parseLong(request.getParameter("cancel")));
    }

    model.addAttribute("manageMsg", msg);
    return "redirect:/admin/managelab";
  }
}
