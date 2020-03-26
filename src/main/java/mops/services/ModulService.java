package mops.services;

import java.util.List;

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

  @Autowired
  private transient ModelService modelService;

  public ModulService(ModulRepository modulRepository, ModelService modelService) {
    this.modulRepository = modulRepository;
    this.modelService = modelService;
  }

  /**
   * Fuegt Modul in die Datenbank hinzu
   * 
   * @param modul Zu speicherndes Modul
   */
  @Override
  public void addModul(ModulDTO modul) {
    modulRepository.save(modul);
  }

  /**
   * Listet alle Module in der Datenbank auf
   * 
   * @return Liste mit allen Modulen
   */
  @Override
  public List<Modul> findAllModule() {
    return modelService.loadModulList(modulRepository.findAll());
  }

  public Modul findModulByModulName(String ModulName) {
    return modelService.loadModul(modulRepository.findModulByModulName(ModulName));
  }

  public void deleteModulByName(String modulName) {
    modulRepository.deleteModulByName(modulName);
  }

  public boolean modulExists(Modul modul) {
    List<ModulDTO> module = modulRepository.findByModulNameAndDozentMail(modul.getModulName(),
        modul.getDozent().getDozentMail());
    if (module != null) {
      return module.size() > 0;
    }
    return false;
  }
}
