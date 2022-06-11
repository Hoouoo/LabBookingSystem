package deu.team.jsp.admin.managelab;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.interceptor.CheckSession;
import deu.team.jsp.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ManageLabController {

  private final ManageLabService manageLabService;
  private final OneTimeKeyService oneTimeKeyService;

  @CheckSession
  @GetMapping("/admin/managelab")
  public String manageLabMainPage(Model model) {
    /**
     * 각각의 리스트 조회
     */
    model.addAttribute("bookList", manageLabService.getAllBookList());
    model.addAttribute("bookApproveList", manageLabService.getAllApproveList());
    model.addAttribute("bookRejectList", manageLabService.getAllRejectList());
    model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
    model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));

    return "/WEB-INF/manager/manageLab.jsp";
  }

  @CheckSession
  @PostMapping("/admin/managelab")
  public String manageLab(HttpServletRequest request, HttpServletResponse response, Model model)
      throws IOException {
    String msg = null;

    if (Objects.nonNull(request.getParameter("approveAll"))) {
      String[] target = request.getParameterValues("itemReject");
      if (Objects.isNull(target)) {
        target = request.getParameterValues("item");
      }

      if (Objects.nonNull(target)) {
        // index[0]은 approveAll의 Value 값이 포함됨
        Long[] targetList = new Long[target.length - 1];
        for (int idx = 1; idx < target.length; idx++) {
          targetList[idx - 1] = Long.parseLong(target[idx]);
        }
        manageLabService.listAllApprove(targetList, ApproveStatus.APPROVE);
      }
    } else {
      if (Objects.nonNull(request.getParameter("rejectAll"))) {
        String[] target = request.getParameterValues("itemApprove");
        if (Objects.isNull(target)) {
          target = request.getParameterValues("item");
        }
        if (Objects.nonNull(target)) {
          // index[0]은 approveAll의 Value 값이 포함됨
          Long[] targetList = new Long[target.length - 1];
          for (int idx = 1; idx < target.length; idx++) {
            targetList[idx - 1] = Long.parseLong(target[idx]);
          }
          manageLabService.listAllApprove(targetList, ApproveStatus.REJECT);
        }
      }
    }

    if (Objects.nonNull(request.getParameter("approve"))) {
      manageLabService.approveBook(Long.parseLong(request.getParameter("approve")));
      msg = "승인되었습니다.";
    } else if (Objects.nonNull(request.getParameter("cancel"))) {
      manageLabService.cancelBook(Long.parseLong(request.getParameter("cancel")));
    } else if (Objects.nonNull(request.getParameter("delete"))) {
      manageLabService.deleteModifyUser(Long.parseLong(request.getParameter("delete")));

    }

    model.addAttribute("manageMsg", msg);
    return "redirect:/admin/managelab";
  }
}
