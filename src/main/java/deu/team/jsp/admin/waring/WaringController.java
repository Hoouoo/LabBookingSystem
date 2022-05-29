package deu.team.jsp.admin.waring;

import deu.team.jsp.account.domain.Account;
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
public class WaringController {

    private final WaringService waringService;

    @CheckSession
    @GetMapping("/admin/waring")
    public String adminWaringPageGet(Model model){
        List<Account> allStudentList = waringService.getAllStudentList();
        if(!allStudentList.isEmpty()){
            model.addAttribute("studentList", allStudentList);
        }

        return "/WEB-INF/manager/adminWaring.jsp";
    }

    @CheckSession
    @PostMapping("/admin/waring")
    public String adminWaringPagePost(HttpServletRequest request){
        String requestWaringStatus = request.getParameter("waring");
        String requestResetStatus = request.getParameter("reset");
        if (Objects.nonNull(requestWaringStatus)){
            log.info(requestWaringStatus);
            waringService.giveWaring(requestWaringStatus);
        }else if(Objects.nonNull(requestResetStatus)){
            log.info(requestWaringStatus);
            waringService.resetWaringCnt(requestResetStatus);
        }

        return "redirect:/admin/waring";
    }
}
