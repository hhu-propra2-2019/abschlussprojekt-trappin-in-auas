package mops.controller;

import mops.domain.models.lehrstuhl.Modul;
import mops.services.ModulService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bewerbung1/orga")
public class OrgaController {

  @Autowired
  private ModulService modulService;

  @GetMapping("/verwaltung")
  @Secured("ROLE_orga")
  public String getBewerbungsVerwaltung(Model m, KeycloakAuthenticationToken token) {
    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    String dozentmail = principal.getKeycloakSecurityContext().getIdToken().getEmail();
    System.out.println(dozentmail);

    m.addAttribute("dozentmail", dozentmail);
    return "Orga/bewerbungenVerwalten";
  }

  @Secured("ROLE_orga")
  @GetMapping("/module")
  public String getModule(Model m, KeycloakAuthenticationToken token) {
    m.addAttribute("modules",modulService.findAllModule());
    return "Orga/moduleEinsehen";
  }

  @Secured("ROLE_orga")
  @PostMapping("/module")
  public String addModule(Model m, KeycloakAuthenticationToken token,
      @RequestParam String modulName) {
    modulService.addModul(new Modul(modulName));
    return "redirect:/bewerbung1/orga/module";
  }
}