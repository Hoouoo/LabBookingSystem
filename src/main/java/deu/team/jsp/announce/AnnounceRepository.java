package deu.team.jsp.announce;

import deu.team.jsp.announce.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceRepository extends JpaRepository<Announcement,Long> {

}
