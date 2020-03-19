package mops.domain.database.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DozentPraeferenzDTO {
  @Id
  private long id;
  
  private String bewerber;
  private String dozentMail; //bzw dozentkennung
  private int praeferenz;

  public DozentPraeferenzDTO(String bewerber, String dozenMail, int praeferenz){
    this.bewerber = bewerber;
    this.dozentMail = dozenMail;
    this.praeferenz = praeferenz;
  }
}

/*
    Dozent | Bewerber | pref
    -------------------------
    jens100| mawin125 | 2
    jens100| alish100 | 3
    jens100| bayab200 | 4
    golov20| alish100 | 1
*/