package deu.team.jsp.admin.waring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaringRepository extends JpaRepository<Waring, Long> {

    Optional<Waring> findByStudentId(String studentId);
}
