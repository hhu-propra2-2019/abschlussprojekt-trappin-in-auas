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
   * falls diese noch nicht existiert
   * @param modul Zu speicherndes Modul
   */

  public void addModul(Modul modul) {
    if(!modulExists(modul)){
      modulRepository.save(dtoService.load(modul));
    }
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

  /**
   * Findet ein Modul in der Datenbank mit dem vorgegebenen Namen
   * @param modulName Name des zu findenden Moduls
   * @return Gefundenes Modul als Model-Objekt oder null
   */
  @Override
  public Modul findModulByModulName(String modulName) {
    return modelService.loadModul(modulRepository.findModulByModulName(modulName));
  }

  /**
   * Löscht ein Modul mit vorgegebenen Namen
   * @param modulName Name des zu löschendes Modul
   */
  @Override
  public void deleteModulByName(String modulName) {
    modulRepository.deleteModulByName(modulName);
  }

  /**
   * Löscht alle module aus der Datenbank
   * praktisch bei Semesterstart
   */
  @Override
  public void deleteAll(){
    modulRepository.deleteAll();
  }

  /**
   * Prüft ob ein ModulDTO zum angegebenen Modul model in der Datenbank existiert
   * @param modul Modul model zu welchem nach einem ModulDTO geprüft wird
   * @return true wenn es ein ModulDTO zum angegebenen Modul model gibt
   */
  @Override
  public boolean modulExists(Modul modul) {
    List<ModulDTO> module = modulRepository.findByModulNameAndDozentMail(modul.getModulName(),
        modul.getDozent().getDozentMail());
    if (module != null) {
      return module.size() > 0;
    }
    return false;
  }
}
