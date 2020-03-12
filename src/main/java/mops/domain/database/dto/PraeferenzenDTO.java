package mops.domain.database.dto;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Table(name = "praeferenzen")
@Entity
public class PraeferenzenDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int maxWunschStunden;
  private int minWunschStunden;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "praeferenzen")
  private List<ModulAuswahlDTO> modulAuswahl;
  private String kommentar;
  @Enumerated(EnumType.STRING)
  private EinstiegTyp einstiegTyp;
  private String einschraenkungen;
  @Embedded
  private BerufModul berufModul;
  @Enumerated(EnumType.STRING)
  private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
