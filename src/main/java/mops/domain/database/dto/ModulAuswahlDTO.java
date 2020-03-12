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
@Table(name = "modulAuswahl")
@Entity
public class ModulAuswahlDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Embedded
  private Modul modul;
  private int prioritaet;
  @ManyToOne
  @JoinColumn(name = "praeferenzen_id")
  private PraeferenzenDTO praeferenzen;
}
