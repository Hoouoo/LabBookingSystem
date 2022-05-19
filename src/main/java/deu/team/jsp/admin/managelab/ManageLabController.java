package deu.team.jsp.admin.managelab;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/admin/managelab")

    public String manageLabMainPage(Model model) {
        model.addAttribute("bookList", manageLabService.getAllBookList());
        model.addAttribute("bookApproveList", manageLabService.getAllApproveList());
        model.addAttribute("bookRejectList", manageLabService.getAllRejectList());

        return "/WEB-INF/manager/manageLab.jsp";
    }

    @PostMapping("/admin/managelab")
    public String manageLab(HttpServletRequest request, HttpServletResponse response,Model model)throws IOException {
        String msg = null;
        if (Objects.nonNull(request.getParameter("approve"))) {
            manageLabService.approveBook(Long.parseLong(request.getParameter("approve")));
            msg = "승인되었습니다.";
        }else if(Objects.nonNull(request.getParameter("cancel"))){
            manageLabService.cancelBook(Long.parseLong(request.getParameter("cancel")));
        }

        model.addAttribute("manageMsg", msg);
        return "redirect:/admin/managelab";
    }
}