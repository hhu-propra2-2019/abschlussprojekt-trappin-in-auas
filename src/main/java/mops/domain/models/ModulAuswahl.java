package mops.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulAuswahl {
  
  @Valid
  private Modul modul;
  private int prioritaet;
  private double note;
  private Beruf beruf;
}
