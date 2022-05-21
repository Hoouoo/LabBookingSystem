package deu.team.jsp.book;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.alert.AlertService;
import deu.team.jsp.announce.AnnounceRepository;
import deu.team.jsp.announce.domain.Announcement;
import deu.team.jsp.book.domain.ApproveStatus;
import deu.team.jsp.book.domain.Book;
import deu.team.jsp.schedule.Schedule;
import deu.team.jsp.schedule.ScheduleRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AnnounceRepository announceRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    AlertService alertService;

    public void book(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession httpSession = request.getSession();
        Account account = (Account) httpSession.getAttribute("account");

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String seat = request.getParameter("seat");
        String labNo = request.getParameter("labNo");


        int year = Integer.valueOf(startTime.substring(0, 4));
        int month = Integer.valueOf(startTime.substring(5, 7));

        int startDay=Integer.valueOf(startTime.substring(8, 10));
        int endDay = Integer.valueOf(endTime.substring(8, 10));

        int startHour = Integer.valueOf(startTime.substring(11, 13));
        int startMinute = Integer.valueOf(startTime.substring(14, 16));
        int endHour = Integer.valueOf(endTime.substring(11, 13));
        int endMinute = Integer.valueOf(endTime.substring(14, 16));

        if (Objects.isNull(seat)) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('수업이 있어 실습실 사용이 제한 되거나 좌석을 선택 해야 합니다.'); location.href='/studentPage';</script>");
            out.flush();
        }

        int seatX = Integer.valueOf(seat.substring(0, 1));
        int seatY = Integer.valueOf(seat.substring(2, 3));

        LocalDateTime start = LocalDateTime.of(year, month, startDay, startHour, startMinute);
        LocalDateTime end = LocalDateTime.of(year, month, endDay, endHour, endMinute);

        Book book = new Book(account.getStudentId(), start, end, labNo, seatX, seatY, ApproveStatus.READY);

        Book findSeat = bookRepository.findSeat(seatX, seatY, labNo, start, end);

        List<Announcement> all = announceRepository.findAll();
        String announceContent = "";
        if (all.size() == 1) { //공지사항 등록 안했을 때
            Announcement announcement = all.get(0);
            announceContent = announcement.getAnnounceContent();
        } else {
            announceContent = "예약이 완료 되었습니다.";
        }
        LocalDateTime now = LocalDateTime.now();
        //해당 아이디에 예약한거 정렬해서 가져와서 끝나는 시간이 현재보다 작으면 예약상태 0으로 변경
        List<Book> lastBookList = bookRepository.getLastBookList(account.getStudentId());
        System.out.println(findSeat);

        if (lastBookList.size() > 0) {
            Book lastBook = lastBookList.get(lastBookList.size() - 1);
            if (lastBook.getEndTime().isBefore(now)) {
                accountRepository.updateBookStatus(account.getStudentId(), 0);
            }
        }

        //예약 상태 검색 쿼리
        int bookStatus = accountRepository.findByStudentId(account.getStudentId()).getBookStatus();

        if (Objects.isNull(findSeat) && bookStatus == 0) { //공지사항 등록 했을 때
            bookRepository.save(book);
            accountRepository.updateBookStatus(account.getStudentId(), 1);

            alertService.alertMessage(announceContent,"",response);

        } else {
            alertService.alertMessage("이미 예약된 좌석 이거나 중복 예약이 불가능 합니다.","/studentPage",response);
        }

    }

    public int[][] checkSeat(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String labNo = request.getParameter("labNo");
        System.out.println("startTime = " + startTime);


        int year = Integer.valueOf(startTime.substring(0, 4));
        int month = Integer.valueOf(startTime.substring(5, 7));

        int startDay=Integer.valueOf(startTime.substring(8, 10));
        int endDay=Integer.valueOf(endTime.substring(8, 10));

        int startHour = Integer.valueOf(startTime.substring(11, 13));
        int startMinute = Integer.valueOf(startTime.substring(14, 16));


        int endHour = Integer.valueOf(endTime.substring(11, 13));
        int endMinute = Integer.valueOf(endTime.substring(14, 16));

        //예약 날자 요일 구하기
        LocalDate targetDate = LocalDate.of(year, month, startDay);
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

        Integer dayOfWeekNumber = Integer.valueOf(request.getParameter("todayDayOfWeek"));
        if ((dayOfWeekNumber == 6 && targetDayOfWeek == 6) || (dayOfWeekNumber == 6 && targetDayOfWeek == 7)
                || (dayOfWeekNumber == 7 && targetDayOfWeek == 7)) {
            alertService.alertMessage("주말에는 주말 좌석 예약 불가능 합니다.","/studentPage",response);
        }

        //새로운 시간 정의 포멧 맞추기
        LocalDateTime start= LocalDateTime.of(year, month, startDay,startHour,startMinute);
        LocalDateTime end= LocalDateTime.of(year, month, endDay,endHour,endMinute);

        LocalTime bookStartTime=LocalTime.of(startHour, startMinute);
        LocalTime bookEndTime=LocalTime.of(endHour, endMinute);

        //다음날 넘어가는지 테스트
        System.out.println("start = " + start);
        System.out.println("end = " + end);



        if(start.isAfter(end)){
            alertService.alertMessage("종료 시간이 시작 시간보다 빠를수 없습니다.","/bookPage",response);
        }

        if (Objects.isNull(labNo)) {
            labNo = "915";
            List<Book> books = bookRepository.bookList(labNo, start, end);
            if (books.size() >= 25) {
                labNo = "916";
                List<Book> books916 = bookRepository.bookList(labNo, start, end);
                if (books916.size() >= 25) {
                    labNo = "918";
                    List<Book> books918 = bookRepository.bookList(labNo, start, end);
                    if (books918.size() >= 25) {
                        labNo = "911";
                    }
                }
            }
        }
        model.addAttribute("labNo", labNo);
        //수업시간 강의실 빈곳 찾기
        int labSizeX = 5;
        int labSizeY = 8;

        int[][] seats = new int[labSizeX][labSizeY];

        for (int i = 0; i < labSizeX; i++) { //빈자리로 초기화
            for (int j = 0; j < labSizeY; j++) {
                seats[i][j] = 0;
            }
        }

        List<Schedule> schedules = scheduleRepository.scheduleList(dayOfWeekKorean, labNo, bookStartTime, bookEndTime);

        if (schedules.size() == 0) { //강의실이 비어있다면

            List<Book> books = bookRepository.bookList(labNo, start, end);

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

    public List<Book> getMyBookList(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Account account = (Account) httpSession.getAttribute("account");

        return bookRepository.MyBookList(account.getStudentId());
    }

    public void removeBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void changeAccountBookStatus(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Account account = (Account) httpSession.getAttribute("account");

        accountRepository.updateBookStatus(account.getStudentId(), 0);
    }

}
