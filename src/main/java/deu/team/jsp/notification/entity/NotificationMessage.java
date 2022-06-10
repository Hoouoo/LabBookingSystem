package deu.team.jsp.notification.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "NOTIFICATION_MESSAGE")
public class NotificationMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "NTFM_ID")
  Long id;

  @Column(name = "NTFM_HASH")
  String hash;

  @Column(name = "NTFM_CONTENT")
  String content;

  @OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Notification> notifications = new ArrayList<>();

}
