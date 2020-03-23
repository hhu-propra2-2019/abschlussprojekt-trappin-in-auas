package mops.domain.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.TutorenSchulungTeilnahme;

import java.util.List;
import javax.persistence.*;

@Data
@Table(name = "praeferenzen")
@Entity
@NoArgsConstructor
public class PraeferenzenDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private int minWunschStunden;
  private int maxWunschStunden;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "praeferenzen", referencedColumnName = "id")
  private List<ModulAuswahlDTO> modulAuswahl;

  private String kommentar;

  @Enumerated(EnumType.STRING)
  private EinstiegTyp einstiegTyp;

  private String einschraenkungen;

  @Enumerated(EnumType.STRING)
  private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

  public PraeferenzenDTO(int minWunschStunden, int maxWunschStunden, List<ModulAuswahlDTO> modulAuswahl,
      String kommentar, EinstiegTyp einstiegTyp, String einschraenkungen,
      TutorenSchulungTeilnahme tutorenSchulungTeilnahme) {
    this.minWunschStunden = minWunschStunden;
    this.maxWunschStunden = maxWunschStunden;
    this.modulAuswahl = modulAuswahl;
    this.kommentar = kommentar;
    this.einstiegTyp = einstiegTyp;
    this.einschraenkungen = einschraenkungen;
    this.tutorenSchulungTeilnahme = tutorenSchulungTeilnahme;
  }
}
