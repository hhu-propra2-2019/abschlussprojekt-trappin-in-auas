package mops.domain.database.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dirigent")
public class ZyklusDirigentDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private boolean bewerbungsZeitraumAktiv;
  private boolean dozentenZeitraumAktiv;
  private boolean verteilerZeitraumAktiv;

  public ZyklusDirigentDTO() {
    this.bewerbungsZeitraumAktiv = false;
    this.dozentenZeitraumAktiv = false;
    this.verteilerZeitraumAktiv = false;
  }
}
