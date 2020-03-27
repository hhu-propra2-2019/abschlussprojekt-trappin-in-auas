package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_VERTEILER;

import mops.domain.models.Bewerber;
import mops.orchestration.VerteilerControllerOrchestrator;
import mops.services.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bewerbung1/verteiler")
public class VerteilerController {
  @Autowired
  private transient VerteilerControllerOrchestrator verteilerControllerOrchestrator;

  @Secured(ROLE_VERTEILER)
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    verteilerControllerOrchestrator.uebersicht(model);
    return "verteiler/verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @GetMapping("/verteilte")
  public String showVerteilteBewerber(Model model, KeycloakAuthenticationToken token) {
    verteilerControllerOrchestrator.verteilte(model);
    return "verteiler/verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @GetMapping("/offene")
  public String showOffeneBewerber(Model model, KeycloakAuthenticationToken token) {
    verteilerControllerOrchestrator.offene(model);
    return "verteiler/verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @GetMapping("/details/{kennung}")
  public String detailansicht(Model model, @PathVariable String kennung) {
    verteilerControllerOrchestrator.details(model, kennung);
    return "bewerbungsdetails/details";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteile")
  public String verteile(@RequestParam String bewerberKennung, @RequestParam String modulName) {
    verteilerControllerOrchestrator.verteile(bewerberKennung, modulName);
    return "redirect:/bewerbung1/verteiler/uebersicht";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteilungentfernen")
  public String verteilungEntfernen(String bewerber, String dozentKennung) {
    verteilerControllerOrchestrator.verteilungenEntfernen(bewerber, dozentKennung);
    return "redirect:/bewerbung1/verteiler/uebersicht/verteilte";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/phasesetzen/{phase}")
  public String phaseSetzen(@PathVariable String phase) {
    verteilerControllerOrchestrator.phaseSetzen(phase);
    return "redirect:/bewerbung1/verteiler/uebersicht";
  }
}