package mops.domain.database.dto;

import lombok.Data;


import javax.persistence.*;

@Data
@Table(name = "modulAuswahl")
@Entity
public class ModulAuswahlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private ModulDTO modul;
    private int prioritaet;
    @ManyToOne
    @JoinColumn(name = "praeferenzen_id")
    private PraeferenzenDTO praeferenzen;
}
