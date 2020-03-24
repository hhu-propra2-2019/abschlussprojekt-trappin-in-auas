package mops.domain.services;

import mops.domain.repositories.ModulRepository;
import mops.services.DTOService;
import mops.services.DozentPraeferenzService;

public interface IDozentService {
  public void fuegePraeferenzHinzu(String bewerber, String dozent, int praeferenz);
}
