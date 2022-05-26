package deu.team.jsp.admin.managelab;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
    // 학생 아이디
    String studentId;

    // 경고 개수
    int waringCnt;
    
}
