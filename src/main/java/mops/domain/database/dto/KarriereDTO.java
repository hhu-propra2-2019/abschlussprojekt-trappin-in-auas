package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "karriere")
@Entity
public class KarriereDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String arbeitserfahrung;
    @Embedded
    private ImmartikulationsStatusDTO immartikulationsStatus;
    @Embedded
    private StudiengangAbschlussDTO fachAbschluss;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "karriere")
    @JoinTable(name = "karriere", joinColumns = @JoinColumn(name = "karriere_id"),
        inverseJoinColumns = @JoinColumn(name = "bestandeneModule_id"))
    private List<BestandeneModuleDTO> bestandendeModule;

}
