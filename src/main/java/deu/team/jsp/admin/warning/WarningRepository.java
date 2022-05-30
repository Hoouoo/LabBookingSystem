package deu.team.jsp.admin.warning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarningRepository extends JpaRepository<Warning, Long> {

    Optional<Warning> findByStudentId(String studentId);
}
