package deu.team.jsp.announce.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class Announcement {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String announceContent;
}
