package mops.domain.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdresseDTO {
  private String PLZ;
  private String wohnort;
  private String strasse;
  private String hausnummer;

  public AdresseDTO(String PLZ, String wohnort, String strasse, String hausnummer) {
    this.PLZ = PLZ;
    this.wohnort = wohnort;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
  }
}