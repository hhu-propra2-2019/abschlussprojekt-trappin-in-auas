package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Modul {

  private String modulName;
}
