package deu.team.jsp.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private String labNo;
    private int seatY;
    private int seatX;

    public Book(String studentId, LocalDateTime startTime, LocalDateTime endTime, String labNo, int seatX, int seatY) {
        this.studentId = studentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.labNo = labNo;
        this.seatX = seatX;
        this.seatY = seatY;
    }
}
