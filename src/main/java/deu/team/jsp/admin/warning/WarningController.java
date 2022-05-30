package deu.team.jsp.admin.warning;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.interceptor.CheckSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WarningController {

    private final WarningService warningService;
    private final OneTimeKeyService oneTimeKeyService;

    @CheckSession
    @GetMapping("/admin/warning")
    public String adminWarningGetPage(Model model){
        List<Account> allStudentList = warningService.getAllStudentList();
        model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
        model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));
        if(!allStudentList.isEmpty()){
            model.addAttribute("studentList", allStudentList);
        }

        return "/WEB-INF/manager/adminWarning.jsp";
    }

    @CheckSession
    @PostMapping("/admin/warning")
    public String adminWarningPostPage(HttpServletRequest request){
        String requestWarningStatus = request.getParameter("warning");
        String requestResetStatus = request.getParameter("reset");
        if (Objects.nonNull(requestWarningStatus)){
            log.info(requestWarningStatus);
            warningService.giveWarning(requestWarningStatus);
        }else if(Objects.nonNull(requestResetStatus)){
            warningService.resetWarning(requestResetStatus);
        }

        return "redirect:/admin/warning";
    }
}
