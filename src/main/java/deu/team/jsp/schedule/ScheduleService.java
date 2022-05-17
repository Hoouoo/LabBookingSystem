package deu.team.jsp.schedule;

import deu.team.jsp.admin.schedule.dto.ScheduleCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .endTime(requestDto.getEndTime()).professor(requestDto.getProfessor()).subject(requestDto.getSubject()).build();

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
}
