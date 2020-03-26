package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mops.domain.models.Beruf;


@Data
@Table(name = "modulAuswahl")
@Entity
@NoArgsConstructor
public class ModulAuswahlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="modul_auswahl_id")
    private long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ModulDTO modul;
    
    private int prioritaet;
    private double note;

    @Enumerated(EnumType.STRING)
    private Beruf beruf;

    public ModulAuswahlDTO(ModulDTO modul, int prioritaet, double note, Beruf beruf) {
        this.modul = modul;
        this.prioritaet = prioritaet;
        this.note = note;
        this.beruf = beruf;
    }

}
