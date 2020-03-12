package mops.domain.database.dto;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
class BerufModul {

  @Enumerated(EnumType.STRING)
  private EinstiegTyp einstiegsTyp;

  @Embedded
  private Modul modul;
}
