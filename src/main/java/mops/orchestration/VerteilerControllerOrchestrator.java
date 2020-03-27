package mops.orchestration;

import java.util.List;
import java.util.stream.Collectors;
import mops.domain.models.Bewerber;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.ModulService;
import mops.services.VerteilerService;
import mops.services.ZyklusDirigentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class VerteilerControllerOrchestrator {

  @Autowired
  BewerberService bewerberService;

  @Autowired
  ModulService modulService;

  @Autowired
  ZyklusDirigentService zyklusDirigentService;

  @Autowired
  VerteilerService verteilerService;

  @Autowired
  DozentPraeferenzService dozentPraeferenzService;

  private void erstelleBasis(Model model){
    List<Bewerber> offeneBewerbungen = bewerberService.findAlleNichtVerteilteBewerber();
    List<Bewerber> zugewieseneBewerbungen = bewerberService.findAlleVerteilteBewerber();
    model.addAttribute("anzahlOffeneBewerbungen", offeneBewerbungen.size());
    model.addAttribute("anzahlZugewieseneBewerbungen", zugewieseneBewerbungen.size());
    model.addAttribute("alleModule", modulService.findAllModule());
    model.addAttribute("verteilerPhase", zyklusDirigentService.getVerteilerPhase());
    model.addAttribute("dozentPhase", zyklusDirigentService.getDozentenPhase());
    model.addAttribute("bewerberPhase", zyklusDirigentService.getBewerbungsPhase());
    model.addAttribute("modulMitZugewiesende", verteilerService.getListModulMitAnzahlVerteilten(modulService.findAllModule()));
  }

  public void uebersicht(Model model) {
    erstelleBasis(model);

    List<Bewerber> offeneBewerbungen = bewerberService.findAlleNichtVerteilteBewerber();
    List<Bewerber> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());
    model.addAttribute("anzeigeModus", "uebersicht");
    model.addAttribute("anzuzeigende", offeneBewerbungenPreview);
  }

  public void verteilte(Model model) {
    erstelleBasis(model);

    List<Bewerber> zugewieseneBewerbungen = bewerberService.findAlleVerteilteBewerber();
    model.addAttribute("anzeigeModus", "verteilte");
    model.addAttribute("anzuzeigende", zugewieseneBewerbungen);
  }

  public void offene(Model model) {
    erstelleBasis(model);

    List<Bewerber> offeneBewerbungen = bewerberService.findAlleNichtVerteilteBewerber();
    model.addAttribute("anzeigeModus", "offene");
    model.addAttribute("anzuzeigende", offeneBewerbungen);
  }

  public void details(Model model, String kennung) {
    model.addAttribute("bewerber", bewerberService.findBewerberModelByKennung(kennung));
    model.addAttribute("phase", zyklusDirigentService.getVerteilerPhase());
  }

  public void verteile(String bewerberKennung, String modulName) {
    bewerberService.verteile(bewerberKennung, modulService.findModulByModulName(modulName).getDozent());
  }

  public void verteilungenEntfernen(String bewerber, String dozentKennung) {
    dozentPraeferenzService.deletePraeferenz(bewerber, dozentKennung);
  }

  public void phaseSetzen(String phase) {
    switch (phase) {
      case "bewerbung":
        zyklusDirigentService.bewerbungsPhaseBeginnen();
        break;
      case "dozent":
        zyklusDirigentService.dozentBewertungsphaseBeginnen();
        break;
      case "verteiler":
        zyklusDirigentService.verteilungsPhaseBeginnen();
        break;
    }
  }
}
