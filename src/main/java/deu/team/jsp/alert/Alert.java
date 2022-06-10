package deu.team.jsp.alert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Alert {

    String message="";
    String href="";

    public Alert(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
