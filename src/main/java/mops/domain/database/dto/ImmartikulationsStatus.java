package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class ImmartikulationsStatus {

  private boolean status;
  private String fachrichtung;

}
