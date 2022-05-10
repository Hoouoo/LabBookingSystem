package deu.team.jsp.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book as b where b.roomNo=:roomNo and b.startTime=:startTime and b.endTime=:endTime")
    List<Book> bookList(@Param("roomNo") int roomNo,
                        @Param("startTime")LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

}
