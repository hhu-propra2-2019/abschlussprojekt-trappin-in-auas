package mops.services;

import java.util.List;
import mops.domain.models.lehrstuhl.Modul;
import mops.domain.repositories.ModulRepository;
import mops.domain.services.IModulService;
import org.springframework.stereotype.Service;

@Service
public class ModulService implements IModulService {

  private ModulRepository modulRepository;

  public ModulService(ModulRepository modulRepository) {
    this.modulRepository = modulRepository;
  }

  @Override
  public void addModul(Modul modul) {
    modulRepository.save(modul);
  }

  @Override
  public Modul findModulById(Long id) {
    return modulRepository.findModulById(id);
  }

  @Override
  public List<Modul> findAllModule() {
    return modulRepository.findAll();
  }
}
