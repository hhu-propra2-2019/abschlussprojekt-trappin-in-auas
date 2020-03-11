package mops.domain.database.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

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
