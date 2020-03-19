package mops.services;

import org.springframework.stereotype.Service;

import mops.domain.models.*;
import mops.domain.repositories.ModulRepository;

@Service
public class DozentService {
  private transient DozentPraeferenzService dozentPraeferenzService;
  private transient DTOService dtoService;
  private transient ModulRepository modulRepository;

  public DozentService(DozentPraeferenzService dozentPraeferenzService, DTOService dtoService, ModulRepository modulRepository) {
    this.dozentPraeferenzService = dozentPraeferenzService;
    this.dtoService = dtoService;
    this.modulRepository = modulRepository;
  }

  public void fuegePraeferenzHinzu(String bewerber, String dozent, int praeferenz){
    DozentPraeferenz dPraeferenz = new DozentPraeferenz(dozent, bewerber, praeferenz);
    dozentPraeferenzService.addPraeferenz(dtoService.load(dPraeferenz));
  }
}