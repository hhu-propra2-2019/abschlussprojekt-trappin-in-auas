
package mops.domain.models;

import java.util.List;

import javax.validation.Valid;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Praeferenzen {

  private int maxWunschStunden;
  private int minWunschStunden;

  @Valid
  private List<ModulAuswahl> modulAuswahl;
  private String kommentar;
  private EinstiegTyp einstiegTyp;
  private String einschraenkungen;
  private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
