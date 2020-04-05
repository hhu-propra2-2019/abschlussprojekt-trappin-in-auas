package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class StudiengangAbschlussDTO {
  private String studiengang;
  private String abschluss;
  private String uni;

  public StudiengangAbschlussDTO(String studiengang, String abschluss, String uni) {
    this.studiengang = studiengang;
    this.abschluss = abschluss;
    this.uni = uni;
  }
}
