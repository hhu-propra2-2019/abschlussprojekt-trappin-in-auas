package mops.domain.services;

import java.util.List;
import mops.domain.models.lehrstuhl.Modul;

public interface IModulService {
  void addModul(Modul modul);

  Modul findModulById(Long id);

  List<Modul> findAllModule();
}
