package deu.team.jsp.admin.schedule.dto;

import deu.team.jsp.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleCreateRequestDto {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String subject;
    private String professor;
    private String labNo;

    public Schedule toEntity(){
        return Schedule.builder().day(day).startTime(startTime).endTime(endTime).subject(subject).professor(professor).labNo(labNo).build();
    }
}
