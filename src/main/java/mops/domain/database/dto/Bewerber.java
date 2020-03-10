package mops.domain.database.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bewerber{

    public Bewerber(String kennung, String nachname, String vorname, String geburtsdatum){
        this.kennung = kennung;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
    }

    public Bewerber(){

    }

    @Id
    private String kennung;
    private String nachname;
    private String vorname;
    private String geburtsdatum;
}
