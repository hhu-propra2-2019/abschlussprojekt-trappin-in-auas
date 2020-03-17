package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;


@Data
@Table(name = "modulAuswahl")
@Entity
public class ModulAuswahlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "modulAuswahl_id")
    private long id;
    
    @OneToOne
    private ModulDTO modul;
    private int prioritaet;
    private double note;

    public ModulAuswahlDTO(ModulDTO modul, int prioritaet) {
        this.modul = modul;
        this.prioritaet = prioritaet;
    }

}
