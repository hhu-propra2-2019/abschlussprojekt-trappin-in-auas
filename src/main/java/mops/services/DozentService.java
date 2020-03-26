package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.models.Bewerber;
import mops.domain.models.DozentPraeferenz;
import mops.domain.services.IDozentService;
import org.springframework.stereotype.Service;

@Service
public class DozentService implements IDozentService {
  private transient DozentPraeferenzService dozentPraeferenzService;

  public DozentService(DozentPraeferenzService dozentPraeferenzService) {
    this.dozentPraeferenzService = dozentPraeferenzService;
  }

  @Override
  public void fuegePraeferenzHinzu(String bewerber, String dozent, int praeferenz){
    DozentPraeferenz dPraeferenz = new DozentPraeferenz(dozent, bewerber, praeferenz);
    dozentPraeferenzService.addPraeferenz(dPraeferenz);
  }

  public List<Bewerber> getBewerbungenMitPraeferenz(List<Bewerber> alleBewerber, String dozent) {
    return alleBewerber.stream()
        .filter(x -> dozentPraeferenzService.getDozentPraeferenz(x.getKennung(), dozent) > -1)
        .collect(Collectors.toList());
  }

  public List<Bewerber> getBewerbungenOhnePraeferenz(List<Bewerber> alleBewerber, String dozent) {
    return alleBewerber.stream()
        .filter(x -> dozentPraeferenzService.getDozentPraeferenz(x.getKennung(), dozent) <= -1)
        .collect(Collectors.toList());
  }
}