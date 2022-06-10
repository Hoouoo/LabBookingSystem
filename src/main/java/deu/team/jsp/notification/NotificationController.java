package deu.team.jsp.notification;

import deu.team.jsp.account.domain.Account;
import deu.team.jsp.interceptor.CheckSession;
import deu.team.jsp.notification.entity.Notification;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

  @Autowired
  NotificationRepository notificationRepository;
  @Autowired
  NotificationService notificationService;

  @CheckSession
  @GetMapping("/notification")
  public String getNotificationPage(HttpSession session, Model model) {

    Account account = (Account) session.getAttribute("account");
    String accountId = account.getStudentId();

    List<Notification> notifications = notificationRepository.findAllByAccountId(accountId);
    model.addAttribute("notifications", notifications);
    model.addAttribute("notificationService", notificationService);

    return "/WEB-INF/notification/notification.jsp";
  }
}
