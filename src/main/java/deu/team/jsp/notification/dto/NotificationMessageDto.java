package deu.team.jsp.notification.dto;

import javax.persistence.*;

@Entity
public class NotificationMessageDto {

    @Id @GeneratedValue
    @Column(name = "NTFM_ID")
    String id;

    @Column(name = "NTFM_CONTENT")
    String content;



}
