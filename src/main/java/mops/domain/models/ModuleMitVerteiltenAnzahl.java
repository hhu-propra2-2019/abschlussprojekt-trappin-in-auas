package mops.domain.models;

import lombok.Data;

@Data
public class ModuleMitVerteiltenAnzahl {
  private Modul modul;
  private int anzhalVerteilte;

  public ModuleMitVerteiltenAnzahl(Modul modul, int anzhalVerteilte){
    this.modul = modul;
    this.anzhalVerteilte = anzhalVerteilte;
  }

}
