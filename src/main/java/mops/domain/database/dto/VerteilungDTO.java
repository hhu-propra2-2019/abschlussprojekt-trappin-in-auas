package mops.domain.database.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class VerteilungDTO {
  @Id
  private long id;

  private String dozentName;
  private String dozentKennung;

  public VerteilungDTO(String dozentName, String dozentKennung){
    this.dozentKennung = dozentKennung;
    this.dozentName = dozentName;
  }
}