package deu.team.jsp.account;

import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import deu.team.jsp.admin.warning.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> getAccountByStudentId(String StudentId);

    Account findByStudentId(String StudentId);

    //좌석 예약 시 예약 상태 업데이트
    @Transactional
    @Modifying
    @Query("update Account a set a.bookStatus=:bookStatus where a.studentId=:studentId")
    void updateBookStatus(@Param("studentId")String studentId,
                          @Param("bookStatus")int bookStatus);

    List<Account> findAllByRole(Role role);

    @Query("select a from Account as a where a.warning.id = :warningId")
    Optional<Account> findByWarning_Id(@Param("warningId") Long warningId);

}
