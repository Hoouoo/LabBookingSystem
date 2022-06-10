package deu.team.jsp.notification.dto;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NotificationDto {

    @Id
    @GeneratedValue
    @Column(name = "NTF_ID")
    String id;

    @Column(name = "ACCOUNT_ID")
    String accountID;

    @Column(name = "CREATED_TIME")
    LocalDateTime createdTime;

    @OneToMany @JoinColumn(name = "NTFM_ID")
    private List<NotificationMessageDto> messages = new ArrayList<>();
}
