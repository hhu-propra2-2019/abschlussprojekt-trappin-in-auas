package mops.domain.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bewerber {

  @Valid
  private Karriere karriere;

  @Valid
  private Personalien personalien;

  @Valid
  private Praeferenzen praeferenzen;

  @NotBlank
  private String kennung;
  
  private List<Dozent> verteiltAn;
  private List<DozentPraeferenz> dozentPraeferenz;

  public Bewerber(Karriere karriere, Personalien personalien, Praeferenzen praeferenzen) {
    this.karriere = karriere;
    this.personalien = personalien;
    this.praeferenzen = praeferenzen;
  }
}
