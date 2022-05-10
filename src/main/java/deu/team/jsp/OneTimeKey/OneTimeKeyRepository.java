package deu.team.jsp.OneTimeKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OneTimeKeyRepository extends JpaRepository<OneTimeKey,Long> {
}
