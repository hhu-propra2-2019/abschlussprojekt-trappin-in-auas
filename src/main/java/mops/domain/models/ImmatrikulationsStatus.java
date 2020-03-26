package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mops.validation.constraints.ImmatrikulationsStatusConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ImmatrikulationsStatusConstraint
public class ImmatrikulationsStatus {
  private boolean status;
  private String fachrichtung;
}
