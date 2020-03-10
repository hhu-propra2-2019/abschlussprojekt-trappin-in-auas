package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "bestandeneModule")
@Entity
public class BestandeneModuleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Modul modul;
    private double note;

    @ManyToOne
    @JoinColumn(name = "karriere_id")
    private KarriereDTO karriere;

}
