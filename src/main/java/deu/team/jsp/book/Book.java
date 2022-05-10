package deu.team.jsp.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private Long id;
    private String studentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int roomNo;
    private int seatY;
    private int seatX;

    public Book(String studentId, LocalDateTime startTime, LocalDateTime endTime, int roomNo, int seatX, int seatY) {
        this.studentId = studentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNo = roomNo;
        this.seatX = seatX;
        this.seatY = seatY;
    }
}
