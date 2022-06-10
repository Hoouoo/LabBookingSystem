package deu.team.jsp.notification.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "NOTIFICATION")
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "NTF_ID")
  Long id;

  @Column(name = "ACCOUNT_ID")
  String accountId;

  @Column(name = "CREATED_TIME")
  LocalDateTime createdTime;

  @Column(name = "IS_CONFIRM")
  Boolean isConfirm;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "NTFM_ID")
  NotificationMessage message;

  public void setMessage(NotificationMessage notificationMessage) {

    if (this.message != null) {
      this.message.getNotifications().remove(this);
    }

    this.message = notificationMessage;
    message.getNotifications().add(this);
  }
}
