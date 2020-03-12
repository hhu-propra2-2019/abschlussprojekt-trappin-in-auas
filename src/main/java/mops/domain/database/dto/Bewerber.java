package mops.domain.database.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Bewerber {

  @Id
  private String kennung;
  private String nachname;
  private String vorname;
  private String geburtsdatum;
  private String verteiltAn;

  public Bewerber(String kennung, String nachname, String vorname, String geburtsdatum) {
    this.kennung = kennung;
    this.nachname = nachname;
    this.vorname = vorname;
    this.geburtsdatum = geburtsdatum;
  }

  public Bewerber(String kennung, String nachname, String vorname, String geburtsdatum,
      String verteiltAn) {
    this.kennung = kennung;
    this.nachname = nachname;
    this.vorname = vorname;
    this.geburtsdatum = geburtsdatum;
    this.verteiltAn = verteiltAn;
  }

  public Bewerber() {

  }
}
