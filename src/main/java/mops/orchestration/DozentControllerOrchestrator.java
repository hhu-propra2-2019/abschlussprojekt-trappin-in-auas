package mops.orchestration;

import java.util.List;
import mops.domain.models.Bewerber;
import mops.domain.models.DozentPraeferenz;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.DozentService;
import mops.services.ZyklusDirigentService;
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

  public void uebersicht(Model model, String dozentMail) {
    erstelleBasis(model, dozentMail, false);
    addAnzeigeModus(model, "uebersicht");
  }

  public void unbearbeitete(Model model, String dozentMail) {
    erstelleBasis(model, dozentMail, false);
    addAnzeigeModus(model, "offene");
  }

  public void bearbeitete(Model model, String dozentMail) {
    erstelleBasis(model, dozentMail, true);
    addAnzeigeModus(model, "vorgemerkte");
  }


  public void detailAnsicht(Model model, String kennung) {
    Bewerber bewerber = bewerberService.findBewerberByKennung(kennung);
    model.addAttribute("bewerber", bewerber);
    model.addAttribute("phase", zyklusDirigentService.getDozentenPhase());
  }

  public void addPreference(String dozentKennung, String bewerberKennung, int praeferenz) {
    dozentPraeferenzService
        .addPraeferenz(new DozentPraeferenz(dozentKennung, bewerberKennung, praeferenz));
  }

  private void erstelleBasis(Model model, String dozentMail, boolean bearbeitete) {
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(dozentMail);
    List<Bewerber> bearbeitet = dozentService
        .getBewerbungenMitPraeferenz(meineBewerber, dozentMail);
    List<Bewerber> nichtBearbeitet = dozentService
        .getBewerbungenOhnePraeferenz(meineBewerber, dozentMail);

    model.addAttribute("dozentPhase", zyklusDirigentService.getDozentenPhase());
    model.addAttribute("bearbeitetCount", bearbeitet.size());
    model.addAttribute("nichtBearbeitetCount", nichtBearbeitet.size());
    model.addAttribute("me", dozentMail);
    if (bearbeitete) {
      model.addAttribute("bewerber", bearbeitet);
    } else {
      model.addAttribute("bewerber", nichtBearbeitet);
    }
  }

  private void addAnzeigeModus(Model model, String anzeigeModus) {
    model.addAttribute("anzeigeModus", anzeigeModus);
  }

}
