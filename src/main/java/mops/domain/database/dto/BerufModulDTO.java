package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.Beruf;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Embeddable
public class BerufModulDTO {
  @Enumerated(EnumType.STRING)
  private Beruf beruf;

  @OneToOne
  private ModulDTO modul;
}
