package deu.team.jsp.account;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.admin.managelab.ManageLabService;
import deu.team.jsp.alert.Alert;
import deu.team.jsp.alert.AlertLastUser;
import deu.team.jsp.alert.AlertService;
import deu.team.jsp.interceptor.CheckSession;
import deu.team.jsp.notification.NotificationService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AccountController {

  @Autowired
  AccountService accountService;

  @Autowired
  OneTimeKeyService oneTimeKeyService;

  @Autowired
  NotificationService notificationService;

  @GetMapping("/")
  public String LoginPage() {
    return "/WEB-INF/account/login.jsp";
  }

  @GetMapping("/signUpPage")
  public String SignUpPage() {
    return "/WEB-INF/account/signUp.jsp";
  }

  @GetMapping("/studentPage")
  public String studentPage(HttpServletRequest request) {
    request.setAttribute("notificationService", notificationService);
    return "WEB-INF/student/studentMain.jsp";
  }

  @GetMapping("/adminPage")
  public String adminPage(Model model) {
    model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
    model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));
    return "WEB-INF/manager/adminMain.jsp";
  }

  @PostMapping("/login")
  public String MainPage(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Role role = accountService.Login(request, response);

    if (role.equals(Role.STUDENT)) {
      return "redirect:/studentPage";
    } else if (role.equals(Role.ADMIN)) {
      return "redirect:/adminPage";
    } else {
      return "redirect:/professorPage";

    }
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return "redirect:/";
  }

  @PostMapping("/signUp")
  public String SignUp(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    accountService.SignUp(request, response);
    return "redirect:/";
  }

  @CheckSession
  @GetMapping("/adminAccountModifyPage")
  public String AdminAccountModifyPageGet(Model model) {
    model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
    model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));
    return "WEB-INF/manager/adminModifyAccount.jsp";
  }

  @CheckSession
  @PostMapping("/adminAccountModifyPage")
  public String AdminAccountModifyPagePost() {
    return "WEB-INF/manager/adminModifyAccount.jsp";
  }

  @CheckSession
  @PostMapping("/accountSearch")
  public String AccountSearch(HttpServletRequest request, HttpServletResponse response, Model model)
      throws IOException {
    model.addAttribute("findAccount", accountService.AccountSearch(request, response, model));
    return "/adminAccountModifyPage";
  }

  @CheckSession
  @PostMapping("/adminAccountModify")
  public String AdminModify(HttpServletRequest request) {
    accountService.modify(request);
    return "redirect:/admin/managelab";
  }

  @AlertLastUser
  @CheckSession
  @GetMapping(value = "/studentAccountModifyPage")
  public String StudentAccountModifyGetPage(HttpSession session, Model model) {
    return "WEB-INF/student/studentModifyAccount.jsp";
  }

  @CheckSession
  @PostMapping(value = "/studentAccountModifyPage")
  public String StudentAccountModifyPostPage() {
    return "WEB-INF/student/studentModifyAccount.jsp";
  }

  @CheckSession
  @PostMapping("/studentAccountModify")
  public String StudentModify(HttpServletRequest request) {
    accountService.modify(request);
    return "redirect:/";
  }


}
