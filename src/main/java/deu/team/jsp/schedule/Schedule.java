package deu.team.jsp.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Schedule {

    @Id @GeneratedValue
    private Long id;
    private String day;
    private Date startTime;
    private Date endTime;
    private String subject;
    private String professor;

}
