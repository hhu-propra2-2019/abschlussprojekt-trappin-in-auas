package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.ModulDTO;
import mops.domain.models.Modul;

public interface IModulService {
  void addModul(ModulDTO modul);

  ModulDTO findModulById(Long id);

  List<Modul> findAllModule();

  void deleteModulByName(String modulName);
}
