package mops.domain.database.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bewerber")
@NoArgsConstructor
public class BewerberDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String kennung;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "personalien")
  private PersonalienDTO personalien;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "karriere")
  private KarriereDTO karriere;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "prefs")
  private PraeferenzenDTO praeferenzen;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "bewerber", referencedColumnName = "id")
  private List<VerteilungDTO> verteiltAn;

  public BewerberDTO(PersonalienDTO personalien, KarriereDTO karriere, PraeferenzenDTO praeferenzen,
      List<VerteilungDTO> verteiltAn) {
    this.personalien = personalien;
    this.karriere = karriere;
    this.praeferenzen = praeferenzen;
    this.verteiltAn = verteiltAn;
  }
}
