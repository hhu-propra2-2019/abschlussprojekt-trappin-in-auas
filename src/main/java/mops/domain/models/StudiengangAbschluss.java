package mops.domain.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudiengangAbschluss {
  @NotBlank
  private String studiengang;

  
  private String abschluss;

  @NotBlank
  private String uni;
}
