package mops.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bewerbung1/orga")
public class OrgaController {


  @GetMapping("/verwaltung")
  @Secured("ROLE_orga")
  public String getBewerbungsVerwaltung(Model model, KeycloakAuthenticationToken token) {
    return "orga/bewerbungenVerwalten";
  }

}
