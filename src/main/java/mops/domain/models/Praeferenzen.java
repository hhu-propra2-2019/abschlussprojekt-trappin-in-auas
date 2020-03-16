
package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
