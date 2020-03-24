package mops.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bewerber {

  private Karriere karriere;
  private Personalien personalien;
  private Praeferenzen praeferenzen;

  private String erstelltVon;
  private List<Dozent> verteiltAn;
}
