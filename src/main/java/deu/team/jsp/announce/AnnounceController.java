package deu.team.jsp.announce;

import deu.team.jsp.OneTimeKey.OneTimeKeyService;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.interceptor.CheckSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @Autowired
    OneTimeKeyService oneTimeKeyService;

    @GetMapping("/announcePage")
    @CheckSession
    public String announcePage(Model model){
        model.addAttribute("context",announceService.findContent());
        model.addAttribute("keyStudent", oneTimeKeyService.getOneTimeKey(Role.STUDENT));
        model.addAttribute("keyProfessor", oneTimeKeyService.getOneTimeKey(Role.PROFESSOR));

        return "/WEB-INF/announce/announce.jsp";
    }

    @PostMapping("/postAnnounce")
    public String postAnnounce(HttpServletRequest request, Model model){
        announceService.post(request);
        return "WEB-INF/manager/adminMain.jsp";
    }

}
