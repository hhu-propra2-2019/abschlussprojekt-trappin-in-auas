package mops.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulAuswahl {
  private Modul modul;
  private int prioritaet;
  private double note;
  private Beruf beruf;
}
