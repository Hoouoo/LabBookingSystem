package deu.team.jsp.notification;

import deu.team.jsp.notification.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

  @Query("select n from Notification as n order by n.createdTime desc")
  List<Notification> findAll();

  @Query("select n from Notification as n where n.accountId = :accountId order by n.createdTime desc")
  List<Notification> findAllByAccountId(@Param("accountId") String accountId);

  @Modifying
  @Transactional
  @Query("update Notification n set n.isConfirm = true where n.accountId = :accountId")
  void updateConfirmToTrue(@Param("accountId") String accountId);

  @Query("select n.id from Notification as n where n.isConfirm = false and n.accountId = :accountId")
  List<Long> findIdes(@Param("accountId") String accountId);
}
