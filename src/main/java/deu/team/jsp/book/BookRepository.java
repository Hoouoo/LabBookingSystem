package deu.team.jsp.book;

import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book as b where b.studentId=:studentId")
    List<Book> MyBookList(@Param("studentId") String studentId);

    //자리 예약 시 강의실, 시작시간, 끝나는 시간, x,y
    @Query("select b from Book as b where b.seatX=:seatX and b.seatY=:seatY and b.labNo=:labNo and  ((:startTime between b.startTime and b.endTime) or (:endTime between b.startTime and b.endTime)" +
            "or (:startTime <= b.startTime) and (:endTime >=b.endTime) )")
    Book findSeat(@Param("seatX") int seatX, @Param("seatY") int seatY,
                  @Param("labNo") String labNo, @Param("startTime") LocalDateTime startTime,
                  @Param("endTime") LocalDateTime endTime);

    //자리 예약 시 해당 강의실, 시작 시간, 끝나는 시간 조회하는 쿼리
    @Query("select b from Book as b where b.labNo=:labNo and ((:start between b.startTime and b.endTime) or (:end between b.startTime and b.endTime)" +
            "or (:start <= b.startTime) and (:end >=b.endTime) )")
    List<Book> bookList(@Param("labNo") String labNo,
                        @Param("start") LocalDateTime start,
                        @Param("end") LocalDateTime end);

    @Query("select b from Book as b where b.studentId=:studentId order by b.endTime")
    List<Book> getLastBookList(@Param("studentId") String studentId);

    @Query("select b from Book as b where b.approveStatus=:approveStatus order by b.id")
    List<Book> getStatusList(@Param("approveStatus") ApproveStatus approveStatus);

//    x,y, 강의실로 예약 가져오기
    @Query("select b from Book as b where b.seatY=:seatY and b.seatX=:seatX and b.startTime>= :startTime and b.labNo=:labNo order by b.startTime")
    List<Book> getBookByX_Y_labNo(@Param("seatX")int seatX,
                            @Param("seatY")int seatY,
                            @Param("labNo")String labNo,
                                  @Param("startTime")LocalDateTime startTime);

    //연장 : 종료 시간 업데이트
    @Transactional
    @Modifying
    @Query("update Book b set b.endTime=:endTime where b.studentId=:studentId")
    void extendEndTime(@Param("endTime")LocalDateTime endTime,
                       @Param("studentId")String studentId);


    @Query("select b from Book as b where b.labNo = :labNo order by b.endTime desc ")
    List<Book> test(@Param("labNo") String labNo);
}
