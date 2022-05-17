package deu.team.jsp.report;

import deu.team.jsp.account.domain.Account;
import deu.team.jsp.report.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public void reportPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession httpSession=request.getSession();
        Account account = (Account) httpSession.getAttribute("account");

        String reportTitle = request.getParameter("reportTitle");
        String reportContent = request.getParameter("reportContent");

        LocalDateTime now=LocalDateTime.now();

        Report report=new Report(account, now,reportTitle,reportContent);

        reportRepository.save(report);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<script>alert('문의사항/신고 가 등록 되었습니다.');</script>");
        out.flush();
    }

    public List<Report> getReportList(){
        return reportRepository.findAll();
    }

    public Report getDetailReport(Long id){
        return reportRepository.findById(id).get();
    }

}
