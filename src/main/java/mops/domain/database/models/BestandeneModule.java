package mops.database.models;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class BestandeneModule {
    @Embedded
    private Modul modul;
    private double note;
    @ManyToOne
    private Karriere karriere;
}
