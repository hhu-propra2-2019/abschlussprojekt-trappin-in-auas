package mops.domain.database.dto;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "verteilung")
public class VerteilungDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String dozentName;
  private String dozentKennung;

  public VerteilungDTO(String dozentName, String dozentKennung){
    this.dozentKennung = dozentKennung;
    this.dozentName = dozentName;
  }
}