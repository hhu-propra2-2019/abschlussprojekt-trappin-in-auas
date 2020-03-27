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
  private ImmatrikulationsStatusDTO immartikulationsStatus;

  @Embedded
  private StudiengangAbschlussDTO fachAbschluss;

  public KarriereDTO(String arbeitserfahrung, ImmatrikulationsStatusDTO immartikulationsStatus,
      StudiengangAbschlussDTO fachAbschluss) {
    this.arbeitserfahrung = arbeitserfahrung;
    this.immartikulationsStatus = immartikulationsStatus;
    this.fachAbschluss = fachAbschluss;
  }
}
