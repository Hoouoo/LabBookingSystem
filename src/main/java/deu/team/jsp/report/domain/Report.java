package deu.team.jsp.report.domain;

import deu.team.jsp.account.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Report {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    private LocalDateTime reportTime;

    private String reportTitle;
    @Lob
    private String reportContent;

    public Report(Account account, LocalDateTime reportTime, String reportTitle, String reportContent) {
        this.account = account;
        this.reportTime = reportTime;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
    }
}
