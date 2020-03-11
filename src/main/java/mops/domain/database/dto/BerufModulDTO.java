package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.Beruf;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Embeddable
class BerufModulDTO {
  @Enumerated(EnumType.STRING)
  private Beruf beruf;

  @Embedded
  private ModulDTO modul;
}
