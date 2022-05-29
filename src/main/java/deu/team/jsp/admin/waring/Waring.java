package deu.team.jsp.admin.waring;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * 학생에게 경고를 주기 위한 테이블
 */
@Entity
@Getter
@NoArgsConstructor
public class Waring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="WARING_ID")
    Long id;
    // 학생 아이디
    String studentId;

    // 경고 개수
    @ColumnDefault("1")
    int waringCnt;

    public void changeWaringCnt(int waringCnt) {
        this.waringCnt = waringCnt;
    }

    @Builder
    public Waring(String studentId, int waringCnt) {
        this.studentId = studentId;
        this.waringCnt = waringCnt;
    }
}
