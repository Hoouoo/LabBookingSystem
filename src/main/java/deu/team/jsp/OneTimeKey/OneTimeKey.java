package deu.team.jsp.OneTimeKey;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class OneTimeKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passKey;

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    @Builder
    public OneTimeKey(String passKey) {
        this.passKey = passKey;
    }
}
