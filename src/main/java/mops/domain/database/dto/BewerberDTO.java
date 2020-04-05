package mops.domain.database.dto;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


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

  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinColumn(name = "bewerber", referencedColumnName = "id")
  private List<VerteilungDTO> verteiltAn = new LinkedList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "bewerberpreaf", referencedColumnName = "id")
  private List<DozentPraeferenzDTO> dozentPraeferenz = new LinkedList<>();

  public BewerberDTO(PersonalienDTO personalien, KarriereDTO karriere, PraeferenzenDTO praeferenzen,
      List<VerteilungDTO> verteiltAn, List<DozentPraeferenzDTO> dozentPraeferenz) {
    this.personalien = personalien;
    this.karriere = karriere;
    this.praeferenzen = praeferenzen;
    this.verteiltAn = verteiltAn;
    this.dozentPraeferenz = dozentPraeferenz;
  }
}
