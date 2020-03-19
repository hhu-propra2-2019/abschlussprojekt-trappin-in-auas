package mops.controllers;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;

import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.models.Bewerber;
import mops.domain.models.DozentPraeferenz;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.DozentService;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  private transient final String orgaRole = "ROLE_orga";

  @Secured({ orgaRole })
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> meineBewerber = bewerberService.findBewerberFuerDozent(token.getName());

    /*
      TODO: anzahl bewerbungen ohne prio und anzahl bewerbungen mit prio
    */

    model.addAttribute("bewerber", meineBewerber);
    model.addAttribute("me", token.getName());
    return "orga/dozent/ubersicht";
  }

  @Secured({orgaRole})
  @PostMapping("/addPreference")
  public String addPreference(Model model, KeycloakAuthenticationToken token, int praeferenz, String dozentKennung, String bewerberKennung){
    dozentPraeferenzService.addPraeferenz(new DozentPraeferenz(dozentKennung, bewerberKennung, praeferenz));
    return "redirect:./uebersicht";
  }

  @Secured({ orgaRole })
  @GetMapping("/uebersicht/offene")
  public String offeneUebersicht(Model model, KeycloakAuthenticationToken token) {
    List<BewerberDTO> offeneBewerbungen = bewerberService.findNichtVerteilt();

    model.addAttribute("offene", offeneBewerbungen);

    return "";
  }

  @Secured({ orgaRole })
  @GetMapping("/uebersicht/zugewiesene")
  public String zugewieseneUebersicht(Model model, KeycloakAuthenticationToken token) {
    List<BewerberDTO> zugewiesene = bewerberService.findVerteilt();

    model.addAttribute(zugewiesene);

    return "";
  }

  @Secured({ orgaRole })
  @GetMapping("/uebersicht/detail")
  public String detailAnsicht(Model model, KeycloakAuthenticationToken token, @PathVariable String kennung) {
    BewerberDTO bewerber = bewerberService.findBewerberByKennung(kennung);
    model.addAttribute("bewerber", bewerber);
    return "";
  }
}