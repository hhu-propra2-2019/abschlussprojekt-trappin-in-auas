package mops.domain.database.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
  private ImmartikulationsStatus immartikulationsStatus;
  @Embedded
  private StudiengangAbschluss fachAbschluss;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "karriere")
  private List<BestandeneModuleDTO> bestandendeModule;

}
