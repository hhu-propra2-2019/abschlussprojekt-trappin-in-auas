package mops.domain.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Adresse {

  @NotBlank
  private String PLZ;

  @NotBlank
  private String wohnOrt;

  @NotBlank
  private String strasse;

  @NotNull //keine ahnung ob es adressen ohne hausnummer gibt
  private String hausnummer;

  public Adresse(String PLZ, String wohnort, String strasse, String hausnummer) {
    this.PLZ = PLZ;
    this.wohnort = wohnort;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
  }
}