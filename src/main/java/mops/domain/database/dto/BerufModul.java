package mops.domain.database.dto;

import lombok.Data;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Embeddable
class BerufModul {
    @Enumerated(EnumType.STRING)
    private EinstiegTyp einstiegsTyp;
    @Embedded
    private Modul modul;
}
