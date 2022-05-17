package deu.team.jsp.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class StartEndTimeDto {

    String subject;
    int labNo;
    int startTime;
    int endTime;
    int day;

    @Builder
    public StartEndTimeDto(String subject, LocalTime startTime, LocalTime endTime,String labNo, String targetDay) {
        this.subject = subject;
        this.startTime = startTime.getHour();
        this.endTime = endTime.getHour();
        this.labNo = filterLabNo(labNo);
        this.day = dayFilter(targetDay);
    }

    private int dayFilter(String day){
        switch (day){
            case "월":
                return 0;
            case "화":
                return 1;
            case "수":
                return 2;
            case "목":
                return 3;
            case "금":
                return 4;
            case "토":
                return 5;
            case "일":
                return 6;
        }
        return -1;
    }
    public int filterLabNo(String labNo){
        switch (labNo){
            case "911":
                return 0;
            case "915":
                return 1;
            case "916":
                return 2;
            case "918":
                return 3;
        }
        return -1;
    }
}
