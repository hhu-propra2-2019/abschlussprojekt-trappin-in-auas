package mops.services;

import org.springframework.stereotype.Service;

import mops.domain.models.*;
import mops.domain.repositories.DozentPraeferenzRepository;

@Service
public class DozentService {
  private transient DozentPraeferenzRepository dozentPraeferenzRepository;
  private transient DTOService dtoService;

  public DozentService(DozentPraeferenzRepository dozentPraeferenzRepository, DTOService dtoService) {
    this.dozentPraeferenzRepository = dozentPraeferenzRepository;
    this.dtoService = dtoService;
  }

  public void fuegePraeferenzHinzu(String bewerber, String dozent, int praeferenz){
    DozentPraeferenz dPraeferenz = new DozentPraeferenz(dozent, bewerber, praeferenz);
    dozentPraeferenzRepository.save(dtoService.load(dPraeferenz));
  }
}