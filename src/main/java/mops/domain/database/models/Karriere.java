package mops.database.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Karriere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String arbeitserfahrung;
    @Embedded
    private ImmartikulationsStatus immartikulationsStatus;
    @Embedded
    private StudiengangAbschluss fachAbschluss;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BestandeneModule> bestandendeModule;

}
