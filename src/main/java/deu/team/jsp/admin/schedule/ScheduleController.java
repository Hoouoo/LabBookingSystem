package deu.team.jsp.admin.schedule;

import deu.team.jsp.admin.schedule.dto.ScheduleCreateRequestDto;
import deu.team.jsp.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/admin/schedule")
    public String schedulePage(Model model){
        //http://localhost:8080/admin/schedule
        if (scheduleService.getScheduleCnt() > 0){
            model.addAttribute("scheduleCnt", scheduleService.getScheduleCnt());
            model.addAttribute("scheduleList", scheduleService.getScheduleList());
        }
        return "/WEB-INF/manager/schedule/scheduleCreate.jsp";
    }
    @PostMapping("/admin/schedule")
    public void scheduleCreate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes model) throws IOException {
        String subject = request.getParameter("subject");
        String professor = request.getParameter("professor");
        String day = request.getParameter("scheduleDay");
        String strStartTime = request.getParameter("scheduleStartTime").toString();
        String strEndTime = request.getParameter("scheduleEndTime").toString();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H[H]:mm");
        LocalTime startTime = LocalTime.parse(strStartTime, formatterTime);
        LocalTime endTime = LocalTime.parse(strEndTime, formatterTime);

        System.out.println("subject : " + subject + " professor : " + professor + " day : "  + day + " startTime : " + startTime + " endTime : " + endTime);

        ScheduleCreateRequestDto requestDto = ScheduleCreateRequestDto.builder().day(day).subject(subject).professor(professor)
                .startTime(startTime).endTime(endTime).build();
        scheduleService.generateSchedule(requestDto);
        response.sendRedirect("/admin/schedule");

    }
}
