package deu.team.jsp.book.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class laboratory {

    @Id
    private Long id;
    private int labNo;
    private int priority;
    private int sizeX;
    private int sizeY;


}
