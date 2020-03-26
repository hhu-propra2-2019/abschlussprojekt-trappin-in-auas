
package mops.domain.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.hibernate.annotations.Cascade;

import lombok.*;
import mops.validation.constraints.EinstiegsTypConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Praeferenzen {

  @Min(7)
  @Max(17)
  private int maxWunschStunden;

  @Min(7)
  @Max(17)
  private int minWunschStunden;

  @Valid
  private List<ModulAuswahl> modulAuswahl;


  private String kommentar;

  @EinstiegsTypConstraint
  private EinstiegTyp einstiegTyp;

  private String einschraenkungen;

  private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
