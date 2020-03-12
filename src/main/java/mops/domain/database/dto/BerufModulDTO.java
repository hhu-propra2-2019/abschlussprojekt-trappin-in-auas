package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.Beruf;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class BerufModulDTO {
  @Enumerated(EnumType.STRING)
  private Beruf beruf;

  @Embedded
  private ModulDTO modul;
}
