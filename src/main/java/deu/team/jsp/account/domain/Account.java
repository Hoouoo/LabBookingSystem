package deu.team.jsp.account.domain;

import deu.team.jsp.admin.warning.Warning;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String studentId;
    private String userName;
    private String userPassword;
    private String email;
    private String phoneNo;

    // 0 : 예약 가능, 1 : 예약 중인 상태, 2 : 블랙리스트 추가된 상태
    private int bookStatus;

    @JoinColumn(name = "WARNING_ID")
    @OneToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Warning warning;


    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(String studentId, String userName, String userPassword, String email, String phoneNo, int bookStatus, Role role) {
        this.studentId = studentId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phoneNo = phoneNo;
        this.bookStatus = bookStatus;
        this.role = role;
    }
}
