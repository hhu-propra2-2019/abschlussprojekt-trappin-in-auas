package mops.domain.database.dto;


import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "karriere")
@Entity
@NoArgsConstructor
public class KarriereDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String arbeitserfahrung;

    @Embedded
    private ImmartikulationsStatusDTO immartikulationsStatus;

    @Embedded
    private StudiengangAbschlussDTO fachAbschluss;

    public KarriereDTO(String arbeitserfahrung,
        ImmartikulationsStatusDTO immartikulationsStatus,
        StudiengangAbschlussDTO fachAbschluss) {
        this.arbeitserfahrung = arbeitserfahrung;
        this.immartikulationsStatus = immartikulationsStatus;
        this.fachAbschluss = fachAbschluss;
    }
}
