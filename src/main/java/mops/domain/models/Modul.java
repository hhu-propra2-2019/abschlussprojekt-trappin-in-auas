package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import mops.validation.constraints.ModulExistsConstraint;

@Data
@AllArgsConstructor
@ModulExistsConstraint
public class Modul {
  private String modulName;
  private Dozent dozent;

  public Modul() {
    this.dozent = new Dozent();
  }
}
