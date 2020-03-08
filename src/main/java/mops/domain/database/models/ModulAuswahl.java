package mops.database.models;

import lombok.Data;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class ModulAuswahl {
    @Embedded
    private Modul modul;
    private int prioritaet;
    @ManyToOne
    private Praeferenzen praeferenzen;

}
