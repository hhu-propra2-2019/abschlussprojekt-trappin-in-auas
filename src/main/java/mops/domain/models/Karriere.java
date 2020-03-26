package mops.domain.models;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Karriere {

  @Size(min = 30)
  private String arbeitserfahrung;

  @Valid
  private ImmartikulationsStatus immartikulationsStatus;

  @Valid
  private StudiengangAbschluss fachAbschluss;

}
