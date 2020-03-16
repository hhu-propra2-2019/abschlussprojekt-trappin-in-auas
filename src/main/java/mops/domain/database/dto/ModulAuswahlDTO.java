package mops.domain.database.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


@Data
@Table(name = "modulAuswahl")
@Entity
public class ModulAuswahlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private ModulDTO modul;
    private int prioritaet;
    private double note;
}
