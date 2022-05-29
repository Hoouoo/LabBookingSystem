package deu.team.jsp.account.domain;

import deu.team.jsp.admin.waring.Waring;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int bookStatus;

    @JoinColumn(name = "WARING_ID")
    @OneToOne(fetch = FetchType.EAGER)
    Waring waring;


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
