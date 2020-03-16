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
    @JoinColumn(name="karriere")
    private KarriereDTO karriere;

    @OneToOne
    @JoinColumn(name="personalien")
    private PersonalienDTO personalien;

    @OneToOne
    @JoinColumn(name="prefs")
    private PraeferenzenDTO praeferenzen;
    
    private String erstelltVon;
    private String verteiltAn;
}
