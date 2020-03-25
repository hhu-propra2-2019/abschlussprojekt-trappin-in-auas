package mops.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bewerber {

  private Karriere karriere;
  private Personalien personalien;
  private Praeferenzen praeferenzen;

  private String kennung;
  private List<Dozent> verteiltAn;
  private List<DozentPraeferenz> dozentPraeferenz;

  public Bewerber(Karriere karriere, Personalien personalien, Praeferenzen praeferenzen){
    this.karriere = karriere;
    this.personalien = personalien;
    this.praeferenzen = praeferenzen;
  }
}
