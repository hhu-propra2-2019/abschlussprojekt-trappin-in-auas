package mops.domain.database.models;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class ModulAuswahl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Modul modul;
    private int prioritaet;



}
