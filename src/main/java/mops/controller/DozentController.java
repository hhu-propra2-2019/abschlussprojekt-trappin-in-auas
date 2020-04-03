package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;

import mops.orchestration.DozentControllerOrchestrator;
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
  private transient DozentControllerOrchestrator dozentControllerOrchestrator;


  @Secured({ ROLE_ORGA })
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    dozentControllerOrchestrator.uebersicht(model, token.getName());
    return "dozent/dozent";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/unbearbeitete")
  public String offeneUebersicht(Model model, KeycloakAuthenticationToken token) {
    dozentControllerOrchestrator.unbearbeitete(model, token.getName());
    return "dozent/dozent";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/bearbeitete")
  public String zugewieseneUebersicht(Model model, KeycloakAuthenticationToken token) {
    dozentControllerOrchestrator.bearbeitete(model, token.getName());
    return "dozent/dozent";
  }

  @Secured({ ROLE_ORGA })
  @PostMapping("/addPreference")
  public String addPreference(int praeferenz, String dozentKennung, String bewerberKennung) {
    dozentControllerOrchestrator.addPreference(dozentKennung, bewerberKennung, praeferenz);
    return "redirect:./uebersicht";
  }

  @Secured({ ROLE_ORGA })
  @GetMapping("/details/{kennung}")
  public String detailAnsicht(Model model, @PathVariable String kennung) {
    dozentControllerOrchestrator.detailAnsicht(model, kennung);
    return "bewerbungsdetails/details";
  }
}