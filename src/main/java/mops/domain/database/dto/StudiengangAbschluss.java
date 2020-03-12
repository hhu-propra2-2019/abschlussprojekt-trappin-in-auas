package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class StudiengangAbschluss {

  private String studiengang;
  private String abschluss;
}
