package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class AdresseDTO {
  private String PLZ;
  private String wohnort;
  private String straße;
  private String hausnummer;

  public AdresseDTO(String PLZ, String wohnort, String straße, String hausnummer) {
    this.PLZ = PLZ;
    this.wohnort = wohnort;
    this.straße = straße;
    this.hausnummer = hausnummer;
  }
}