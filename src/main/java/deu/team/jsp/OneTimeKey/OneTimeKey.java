package deu.team.jsp.OneTimeKey;

import deu.team.jsp.account.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author 일회용 토큰관련 엔티디
 */

@Entity
@Getter
@NoArgsConstructor
public class OneTimeKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passKey;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    @Builder
    public OneTimeKey(String passKey, Role role) {
        this.passKey = passKey;
        this.role = role;
    }
}
