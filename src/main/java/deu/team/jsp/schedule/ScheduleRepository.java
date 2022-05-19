package deu.team.jsp.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule as s where s.day=:day and s.labNo=:labNo and ((:bookStartTime between s.startTime and s.endTime) or (:bookEndTime between s.startTime and s.endTime)" +
            "or (:bookStartTime <= s.startTime) and (:bookEndTime >=s.endTime) )")
    List<Schedule> scheduleList(@Param("day")String day,
                                @Param("labNo")String labNo,
                                @Param("bookStartTime") LocalTime bookStartTime,
                                @Param("bookEndTime")LocalTime bookEndTime);

}
