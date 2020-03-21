package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.database.dto.ModulDTO;
import mops.domain.models.Modul;
import mops.domain.repositories.ModulRepository;
import mops.domain.services.IModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModulService implements IModulService {

  @Autowired
  private transient ModulRepository modulRepository;

  private transient ModelService modelService;

  public ModulService(ModulRepository modulRepository, ModelService modelService) {
    this.modulRepository = modulRepository;
    this.modelService = modelService;
  }

  /**
   * Fuegt Modul in die Datenbank hinzu
   * @param modul Zu speicherndes Modul
   */
  @Override
  public void addModul(ModulDTO modul) {
    modulRepository.save(modul);
  }

  /**
   * Gibt Modul mit gegebener Id zurück
   * @param id id des Moduls
   * @return Modul mit id
   */
  @Override
  public ModulDTO findModulById(Long id) {
    return modulRepository.findModulById(id);
  }

  /**
   * Listet alle Module in der Datenbank auf
   * @return Liste mit allen Modulen
   */
  @Override
  public List<Modul> findAllModule() {
    return modulRepository.findAll().stream().map(x -> modelService.loadModul(x)).collect(Collectors.toList());
  }

  public void deleteModulByName(String modulName) {
    modulRepository.deleteModulByName(modulName);
  }
}
