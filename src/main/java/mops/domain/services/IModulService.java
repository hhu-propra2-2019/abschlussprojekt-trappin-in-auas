package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.ModulDTO;
import mops.domain.models.Modul;

public interface IModulService {
  void addModul(ModulDTO modul);

  List<Modul> findAllModule();

  void deleteModulByName(String modulName);
}
