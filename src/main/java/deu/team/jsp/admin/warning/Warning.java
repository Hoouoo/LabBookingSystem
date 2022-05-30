package deu.team.jsp.admin.warning;

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
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="WARNING_ID")
    Long id;
    // 학생 아이디
    String studentId;

    // 경고 개수
    @ColumnDefault("1")
    int warningCnt;

    public void changeWarningCnt(int warningCnt) {
        this.warningCnt = warningCnt;
    }

    @Builder
    public Warning(String studentId, int warningCnt) {
        this.studentId = studentId;
        this.warningCnt = warningCnt;
    }
}
