
package mops.domain.database.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Praeferenzen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maxWunschStunden;
    private int minWunschStunden;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ModulAuswahl> modulAuswahl;
    private String kommentar;
    @Enumerated(EnumType.STRING)
    private EinstiegTyp einstiegTyp;
    private String einschraenkungen;
    @Embedded
    private BerufModul berufModul;
    @Enumerated(EnumType.STRING)
    private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
