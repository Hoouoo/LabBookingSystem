package deu.team.jsp.professor;

import deu.team.jsp.admin.schedule.dto.ScheduleCreateRequestDto;
import deu.team.jsp.interceptor.CheckSession;
import deu.team.jsp.nowBookStatus.NowBookStatusService;
import deu.team.jsp.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ProfessorController {

    private final NowBookStatusService nowBookStatusService;
    private final ScheduleService scheduleService;


    @CheckSession
    @GetMapping("/professorPage")
    public String professorPage(Model model) {

        if (scheduleService.getScheduleCnt() > 0) {
            model.addAttribute("scheduleCnt", scheduleService.getScheduleCnt());
            model.addAttribute("scheduleList", scheduleService.getScheduleList());
            model.addAttribute("scheduleTimeList", scheduleService.getSubjectTime());

        }
        return "/WEB-INF/professor/professorMain.jsp";
    }

    @PostMapping("/prof/schedule")
    public void profSchedulePagePost(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (Objects.nonNull(request.getParameter("delete"))) {
            scheduleService.deleteSchedule(Long.parseLong(request.getParameter("delete")));
        } else {
            String subject = request.getParameter("subject");
            String professor = request.getParameter("professor");
            String labNo = request.getParameter("labNo");
            String day = request.getParameter("scheduleDay");
            String strStartTime = request.getParameter("scheduleStartTime").toString();
            String strEndTime = request.getParameter("scheduleEndTime").toString();
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H[H]:mm");
            LocalTime startTime = LocalTime.parse(strStartTime, formatterTime);
            LocalTime endTime = LocalTime.parse(strEndTime, formatterTime);

            System.out.println("subject : " + subject + " professor : " + professor + " day : " + day + " startTime : " + startTime + " endTime : " + endTime);

            ScheduleCreateRequestDto requestDto = ScheduleCreateRequestDto.builder().day(day).labNo(labNo).subject(subject).professor(professor)
                    .startTime(startTime).endTime(endTime).build();
            // dto 저장
            scheduleService.generateSchedule(requestDto);
        }
        response.sendRedirect("/admin/schedule");
    }


    @CheckSession
    @GetMapping("/prof/schedule")
    public String profSchedulePage() {
        //TODO 학생 헤더가 아닌 교수 헤더로 변경되야 함.
        return "/WEB-INF/manager/schedule/scheduleCreate.jsp";
    }


    @CheckSession
    @GetMapping("/prof/nowLabStatusPage")
    public String profSearchSeat(Model model, HttpServletRequest request) {
        model.addAttribute("seats", nowBookStatusService.nowBookStatus(request, model));
        return "/WEB-INF/student/nowLabStatus.jsp";
    }
}
