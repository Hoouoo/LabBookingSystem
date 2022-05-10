package deu.team.jsp.OneTimeKey;

import deu.team.jsp.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author 일회용 토큰 관련 JPA Repository
 */
@Repository
@Transactional(readOnly = true)
public interface OneTimeKeyRepository extends JpaRepository<OneTimeKey,Long> {

    Optional<OneTimeKey> findByRole(Role role);

}
