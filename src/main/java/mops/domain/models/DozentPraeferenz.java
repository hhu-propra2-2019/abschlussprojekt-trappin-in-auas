package mops.domain.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DozentPraeferenz {
  private String dozentKennung;
  private String bewerberKennung;
  private int praeferenz;
}