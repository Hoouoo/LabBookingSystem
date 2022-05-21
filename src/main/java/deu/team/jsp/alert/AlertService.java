package deu.team.jsp.alert;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class AlertService {

    public void alertMessage(String msg, String redirectPage, HttpServletResponse response) throws IOException {
        String str="";
        if(redirectPage.isEmpty()){
            str="<script>alert('" + msg + "');</script>";
        }
        else{
            str=("<script>alert('" + msg + "'); location.href='" + redirectPage + "';</script>");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(str);
        out.flush();
    }
}
