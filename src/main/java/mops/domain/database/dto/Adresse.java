package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Adresse {

  private String PLZ;
  private String wohnort;
  private String wohnstadt;
  private String straße;
  private String hausnummer;
}