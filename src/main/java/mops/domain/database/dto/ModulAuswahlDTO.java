package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public ModulAuswahlDTO(ModulDTO modul, int prioritaet, double note) {
        this.modul = modul;
        this.prioritaet = prioritaet;
        this.note = note;
    }

}
