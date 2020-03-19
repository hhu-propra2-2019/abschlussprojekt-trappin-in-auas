
package mops.domain.models;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Praeferenzen {

    private int maxWunschStunden;
    private int minWunschStunden;
    private List<ModulAuswahl> modulAuswahl;
    private String kommentar;
    private EinstiegTyp einstiegTyp;
    private String einschraenkungen;
    private BerufModul berufModul;
    private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
