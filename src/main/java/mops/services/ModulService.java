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

  private transient ModulRepository modulRepository;

  @Autowired
  private transient ModelService modelService;

  @Autowired
  private transient DTOService dtoService;

  public ModulService(ModulRepository modulRepository, ModelService modelService) {
    this.modulRepository = modulRepository;
  }

  /**
   * Fuegt Modul in die Datenbank hinzu
   * @param modul Zu speicherndes Modul
   */

  public void addModul(Modul modul) {
    modulRepository.save(dtoService.load(modul));
  }

  @Override
  public void addModul(ModulDTO modul) {
    modulRepository.save(modul);
  }

  /**
   * Listet alle Module in der Datenbank auf
   * @return Liste mit allen Modulen
   */
  @Override
  public List<Modul> findAllModule() {
    return modelService.loadModulList(modulRepository.findAll());
  }


  public Modul findModulByModulName(String ModulName){
    return modelService.loadModul(modulRepository.findModulByModulName(ModulName));
  }

  public void deleteModulByName(String modulName) {
    modulRepository.deleteModulByName(modulName);
  }

  public boolean modulExists(Modul modul) {
    List<ModulDTO> module = modulRepository.findByModulNameAndDozentMail(modul.getModulName(), modul.getDozent().getDozentMail());
    if(module != null){
      return module.size() > 0;
    }
    return false;
  }
}
