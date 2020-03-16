package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.TutorenSchulungTeilnahme;

import java.util.List;
import javax.persistence.*;
import lombok.Data;


@Data
@Table(name = "praeferenzen")
@Entity
public class PraeferenzenDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int minWunschStunden;
    private int maxWunschStunden;
    
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

    public PraeferenzenDTO(int minWunschStunden, int maxWunschStunden,
        List<ModulAuswahlDTO> modulAuswahl, String kommentar,
        EinstiegTyp einstiegTyp, String einschraenkungen,
        BerufModulDTO berufModul, TutorenSchulungTeilnahme tutorenSchulungTeilnahme) {
        this.minWunschStunden = minWunschStunden;
        this.maxWunschStunden = maxWunschStunden;
        this.modulAuswahl = modulAuswahl;
        this.kommentar = kommentar;
        this.einstiegTyp = einstiegTyp;
        this.einschraenkungen = einschraenkungen;
        this.berufModul = berufModul;
        this.tutorenSchulungTeilnahme = tutorenSchulungTeilnahme;
    }
}
