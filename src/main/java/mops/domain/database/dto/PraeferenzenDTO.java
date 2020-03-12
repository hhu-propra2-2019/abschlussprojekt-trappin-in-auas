package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.TutorenSchulungTeilnahme;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "praeferenzen")
@Entity
public class PraeferenzenDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int maxWunschStunden;
    private int minWunschStunden;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "praeferenzen", joinColumns = @JoinColumn(name = "praeferenzen_id"),
        inverseJoinColumns = @JoinColumn(name = "modulAuswahl_id"))
    private List<ModulAuswahlDTO> modulAuswahl;
    private String kommentar;
    @Enumerated(EnumType.STRING)
    private EinstiegTyp einstiegTyp;
    private String einschraenkungen;
    @Embedded
    private BerufModulDTO berufModul;
    @Enumerated(EnumType.STRING)
    private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
