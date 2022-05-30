package deu.team.jsp.alert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlertLastUserDto {

    private String labNo;
    private String studentId;

    @Builder

    public AlertLastUserDto(String labNo, String studentId) {
        this.labNo = labNo;
        this.studentId = studentId;
    }
}
