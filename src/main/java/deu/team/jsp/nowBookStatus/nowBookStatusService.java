package deu.team.jsp.nowBookStatus;

import deu.team.jsp.book.BookRepository;
import deu.team.jsp.book.domain.Book;
import deu.team.jsp.schedule.Schedule;
import deu.team.jsp.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.util.List;
import java.util.Objects;

@Service
public class nowBookStatusService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public int[][] nowBookStatus(HttpServletRequest request, Model model){

        LocalDateTime startTime=LocalDateTime.now();
        LocalDateTime endTime=startTime.plusMinutes(1);

        String labNo = request.getParameter("labNo");

        if(Objects.isNull(labNo)){
            labNo="915";
        }
        model.addAttribute("labNo",labNo);
        int year = startTime.getYear();
        int monthValue = startTime.getMonthValue();
        int day=startTime.getDayOfMonth();

        LocalDate targetDate = LocalDate.of(year,monthValue,day);
        DayOfWeek dayOfWeek = targetDate.getDayOfWeek();
        int targetDayOfWeek = dayOfWeek.getValue();
        String dayOfWeekKorean = "";
        if (targetDayOfWeek == 1) {
            dayOfWeekKorean = "월";
        } else if (targetDayOfWeek == 2) {
            dayOfWeekKorean = "화";
        } else if (targetDayOfWeek == 3) {
            dayOfWeekKorean = "수";
        } else if (targetDayOfWeek == 4) {
            dayOfWeekKorean = "목";
        } else if (targetDayOfWeek == 5) {
            dayOfWeekKorean = "금";
        } else if (targetDayOfWeek == 6) {
            dayOfWeekKorean = "토";
        } else if (targetDayOfWeek == 7) {
            dayOfWeekKorean = "일";
        }

        //수업시간 강의실 빈곳 찾기
        int labSizeX = 5;
        int labSizeY = 8;

        int[][] seats = new int[labSizeX][labSizeY];

        for (int i = 0; i < labSizeX; i++) { //빈자리로 초기화
            for (int j = 0; j < labSizeY; j++) {
                seats[i][j] = 0;
            }
        }

        LocalTime bookStartTime=LocalTime.of(startTime.getHour(),startTime.getMinute());
        LocalTime bookEndTime=LocalTime.of(startTime.getHour(), endTime.getMinute());

        List<Schedule> schedules = scheduleRepository.scheduleList(dayOfWeekKorean, labNo, bookStartTime, bookEndTime);

        if (schedules.size() == 0) { //강의실이 비어있다면

            List<Book> books = bookRepository.bookList(labNo, startTime, endTime);

            for (Book book : books) {
                for (int i = 0; i < labSizeX; i++) {
                    for (int j = 0; j < labSizeY; j++) {
                        if (book.getSeatX() - 1 == j && book.getSeatY() - 1 == i) {
                            seats[i][j] = 1;
                        }
                    }
                }
            }

        } else {

            for (int i = 0; i < labSizeX; i++) {
                for (int j = 0; j < labSizeY; j++) {
                    seats[i][j] = 1;
                }
            }
        }

        return seats;
    }

}
