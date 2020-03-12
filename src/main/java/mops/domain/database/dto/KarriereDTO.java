package mops.domain.database.dto;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "karriere", joinColumns = @JoinColumn(name = "karriere_id"),
        inverseJoinColumns = @JoinColumn(name = "bestandeneModule_id"))
    private List<BestandeneModuleDTO> bestandendeModule;

}
