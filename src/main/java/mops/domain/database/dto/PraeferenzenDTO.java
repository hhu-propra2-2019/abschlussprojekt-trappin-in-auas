package mops.domain.database.dto;

import lombok.Data;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.TutorenSchulungTeilnahme;

import java.util.List;
import javax.persistence.*;

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
    @JoinTable(name = "modulAuswahl", 
      joinColumns = @JoinColumn(name = "id"),
      inverseJoinColumns = @JoinColumn(name = "modul_auswahl_id"))
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
