package deu.team.jsp.schedule;

import deu.team.jsp.admin.schedule.dto.ScheduleCreateRequestDto;
import deu.team.jsp.schedule.dto.StartEndTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Schedule 관련 Service
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void generateSchedule(ScheduleCreateRequestDto requestDto){
        Schedule target = Schedule.builder().day(requestDto.getDay()).startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime()).labNo(requestDto.getLabNo()).professor(requestDto.getProfessor()).subject(requestDto.getSubject()).build();

        scheduleRepository.save(target);
    }

    public List<Schedule> getScheduleList(){
        return scheduleRepository.findAll();
    }

    public int getScheduleCnt(){
        if(scheduleRepository.findById(1L).isEmpty()){
            return 0;
        }
        return scheduleRepository.findAll().size();
    }

    public Optional<Schedule> getSchedule(Long id){
        return scheduleRepository.findById(id);
    }

    // 과목이 중복되지 않고 하나만 존재한 경우
    public List<StartEndTimeDto> getSubjectTime(){
        if(getScheduleCnt() == 0){
            return null;
        }else{
            List<Schedule> targetScheduleList = scheduleRepository.findAll();
            List<StartEndTimeDto> targetStartTimeList = new ArrayList<>();
            for(Schedule target : targetScheduleList) {
                StartEndTimeDto dto = StartEndTimeDto.builder().labNo(target.getLabNo()).subject(target.getSubject()).startTime(target.getStartTime()).endTime(target.getEndTime()).targetDay(target.getDay()).build();
                targetStartTimeList.add(dto);
            }
            return targetStartTimeList;
        }
    }
}
