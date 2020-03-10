
package mops.domain.models;

import lombok.Data;

import java.util.List;

@Data
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
