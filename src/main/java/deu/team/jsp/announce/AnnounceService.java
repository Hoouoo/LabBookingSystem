package deu.team.jsp.announce;

import deu.team.jsp.announce.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AnnounceService {

    @Autowired
    AnnounceRepository announceRepository;

    public String findContent(){

        List<Announcement> all = announceRepository.findAll();

        if(all.size() == 1) {
            Announcement announcement = all.get(0);
            String announceContent = announcement.getAnnounceContent();
            return announceContent;
        }
        else{
            return "";
        }
    }

    public void post(HttpServletRequest request){

        String announceContent = request.getParameter("announceContent");
        Announcement announcement=new Announcement();
        announcement.setAnnounceContent(announceContent);
        announceRepository.deleteAll();
        announceRepository.save(announcement);
    }

}
