package mops.orchestration;

import java.util.List;
import mops.domain.models.Bewerber;
import mops.domain.models.DozentPraeferenz;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.DozentService;
import mops.services.ZyklusDirigentService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DozentControllerOrchestrator {

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient DozentService dozentService;

  @Autowired
  private transient ZyklusDirigentService zyklusDirigentService;

  @Autowired
  private transient DozentPraeferenzService dozentPraeferenzService;

  public void erstelleBasis(Model model, KeycloakAuthenticationToken token, boolean bearbeitete){
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(token.getName());
    List<Bewerber> bearbeitet = dozentService.getBewerbungenMitPraeferenz(meineBewerber, token.getName());
    List<Bewerber> nichtBearbeitet = dozentService.getBewerbungenOhnePraeferenz(meineBewerber, token.getName());

    model.addAttribute("dozentPhase", zyklusDirigentService.getDozentenPhase());
    model.addAttribute("bearbeitetCount", bearbeitet.size());
    model.addAttribute("nichtBearbeitetCount", nichtBearbeitet.size());
    model.addAttribute("me", token.getName());
    if(bearbeitete) {
      model.addAttribute("bewerber", bearbeitet);
    }
    else {
      model.addAttribute("bewerber", nichtBearbeitet);
    }
  }


  public void addAnzeigeModus(Model model,String anzeigeModus){
    model.addAttribute("anzeigeModus", anzeigeModus);
  }

  public void detailAnsicht(Model model, String kennung){
    Bewerber bewerber = bewerberService.findBewerberByKennung(kennung);
    model.addAttribute("bewerber", bewerber);
    model.addAttribute("phase", zyklusDirigentService.getDozentenPhase());
  }

  public void addPreference(String dozentKennung, String bewerberKennung, int praeferenz){
    dozentPraeferenzService.addPraeferenz(new DozentPraeferenz(dozentKennung, bewerberKennung, praeferenz));
  }
}
