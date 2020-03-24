package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;

import java.util.List;
import mops.domain.models.Bewerber;
import mops.domain.models.DozentPraeferenz;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.DozentService;

import mops.services.ModelService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bewerbung1/dozent")
public class DozentController {

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient DozentService dozentService;

  @Autowired
  private transient DozentPraeferenzService dozentPraeferenzService;

  @Autowired
  private transient ModelService modelService;

  private static final String BEWERBER_ATTRIBUTE = "bewerber";

  @Secured({ ROLE_ORGA })
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(token.getName());
    List<Bewerber> bearbeitet = dozentService.getBewerbungenMitPraeferenz(meineBewerber, token.getName());
    List<Bewerber> nichtBearbeitet = dozentService.getBewerbungenOhnePraeferenz(meineBewerber, token.getName());

    model.addAttribute("bearbeitetCount", bearbeitet.size());
    model.addAttribute("nichtBearbeitetCount", nichtBearbeitet.size());
    model.addAttribute("me", token.getName());
    model.addAttribute(bewerberAttribute, nichtBearbeitet);
    return "orga/dozent/ubersicht";
  }

  @Secured({ ROLE_ORGA })
  @PostMapping("/addPreference")
  public String addPreference(Model model, KeycloakAuthenticationToken token, int praeferenz, String dozentKennung,
      String bewerberKennung) {
    dozentPraeferenzService.addPraeferenz(new DozentPraeferenz(dozentKennung, bewerberKennung, praeferenz));
    return "redirect:./uebersicht";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/unbearbeitete")
  public String offeneUebersicht(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(token.getName());
    List<Bewerber> bearbeitet = dozentService.getBewerbungenMitPraeferenz(meineBewerber, token.getName());
    List<Bewerber> nichtBearbeitet = dozentService.getBewerbungenOhnePraeferenz(meineBewerber, token.getName());

    model.addAttribute("bearbeitetCount", bearbeitet.size());
    model.addAttribute("nichtBearbeitetCount", nichtBearbeitet.size());
    model.addAttribute("me", token.getName());
    model.addAttribute(bewerberAttribute, nichtBearbeitet);

    model.addAttribute("anzeigeModus", "offene");
    return "dozent/dozent";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/bearbeitete")
  public String zugewieseneUebersicht(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(token.getName());
    List<Bewerber> bearbeitet = dozentService.getBewerbungenMitPraeferenz(meineBewerber, token.getName());
    List<Bewerber> nichtBearbeitet = dozentService.getBewerbungenOhnePraeferenz(meineBewerber, token.getName());

    model.addAttribute("bearbeitetCount", bearbeitet.size());
    model.addAttribute("nichtBearbeitetCount", nichtBearbeitet.size());
    model.addAttribute("me", token.getName());
    model.addAttribute(bewerberAttribute, bearbeitet);

    model.addAttribute("anzeigeModus", "vorgemerkte");
    return "dozent/dozent";
  }

  @Secured({ ROLE_ORGA })
  @PostMapping("/addPreference")
  public String addPreference(Model model, KeycloakAuthenticationToken token, int praeferenz, String dozentKennung,
                              String bewerberKennung) {
    dozentPraeferenzService.addPraeferenz(new DozentPraeferenz(dozentKennung, bewerberKennung, praeferenz));
    return "redirect:./uebersicht";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/details/{kennung}")
  public String detailAnsicht(Model model, KeycloakAuthenticationToken token, @PathVariable String kennung) {
    BewerberDTO bewerberDTO = bewerberService.findBewerberByKennung(kennung);
    Bewerber bewerber = modelService.load(bewerberDTO);
    model.addAttribute(bewerberAttribute, bewerber);
    return "bewerbungsdetails/details";
  }
}