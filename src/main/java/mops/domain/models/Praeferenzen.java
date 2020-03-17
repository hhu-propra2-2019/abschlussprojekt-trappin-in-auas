
package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
