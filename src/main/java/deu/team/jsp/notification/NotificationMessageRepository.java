package deu.team.jsp.notification;

import deu.team.jsp.notification.entity.NotificationMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Long> {

}
