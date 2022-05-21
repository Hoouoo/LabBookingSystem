package deu.team.jsp.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String studentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String labNo;
    private int seatY;
    private int seatX;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    public Book(String studentId, LocalDateTime startTime, LocalDateTime endTime, String labNo, int seatX, int seatY,ApproveStatus approveStatus) {
        this.studentId = studentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.labNo = labNo;
        this.seatX = seatX;
        this.seatY = seatY;
        this.approveStatus = approveStatus;
    }
}
