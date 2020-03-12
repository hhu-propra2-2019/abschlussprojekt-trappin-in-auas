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
    private ModulDTO modul;
    private double note;
}
