package deu.team.jsp.account.domain;

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
    private Long id;
    private String studentId;
    private String userName;
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(String studentId, String userName, String userPassword) {
        this.studentId = studentId;
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
