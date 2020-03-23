package mops.services;

import mops.domain.models.DozentPraeferenz;
import mops.domain.services.IDozentService;
import org.springframework.stereotype.Service;

@Service
public class DozentService implements IDozentService {
  private transient DozentPraeferenzService dozentPraeferenzService;
  private transient DTOService dtoService;

  public DozentService(DozentPraeferenzService dozentPraeferenzService, DTOService dtoService) {
    this.dozentPraeferenzService = dozentPraeferenzService;
    this.dtoService = dtoService;
  }

  @Override
  public void fuegePraeferenzHinzu(String bewerber, String dozent, int praeferenz){
    DozentPraeferenz dPraeferenz = new DozentPraeferenz(dozent, bewerber, praeferenz);
    dozentPraeferenzService.addPraeferenz(dtoService.load(dPraeferenz));
  }
}