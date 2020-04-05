package mops.domain.services;

import java.util.List;

import mops.domain.models.Modul;

public interface IModulService {
 
  public void addModul(Modul modul);

  public void deleteModulByName(String modulName);

  public void deleteAll();

  public List<Modul> findAllModule();

  public Modul findModulByModulName(String ModulName);
  
  public boolean modulExists(Modul modul);
}
