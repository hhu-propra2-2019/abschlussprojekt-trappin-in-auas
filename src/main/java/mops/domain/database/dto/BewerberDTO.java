package mops.domain.database.dto;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "bewerber")
public class BewerberDTO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="personalien")
    private PersonalienDTO personalien;

    @OneToOne
    @JoinColumn(name="karriere")
    private KarriereDTO karriere;

    @OneToOne
    @JoinColumn(name="prefs")
    private PraeferenzenDTO praeferenzen;

    private String kennung;
    private String verteiltAn;

    public BewerberDTO(PersonalienDTO personalien, KarriereDTO karriere,
        PraeferenzenDTO praeferenzen, String verteiltAn) {
        this.personalien = personalien;
        this.karriere = karriere;
        this.praeferenzen = praeferenzen;
        this.verteiltAn = verteiltAn;
    }
}
