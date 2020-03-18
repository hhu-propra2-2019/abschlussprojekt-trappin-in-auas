package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.ModulDTO;

public interface IModulService {
  void addModul(ModulDTO modul);

  ModulDTO findModulById(Long id);

  List<ModulDTO> findAllModule();

  void deleteModulByName(String modulName);
}
