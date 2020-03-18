package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;

import org.keycloak.KeycloakPrincipal;
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
  @Secured(ROLE_ORGA)
  public String getBewerbungsVerwaltung(Model m, KeycloakAuthenticationToken token) {
    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    String dozentmail = principal.getKeycloakSecurityContext().getIdToken().getEmail();

    m.addAttribute("dozentmail", dozentmail);
    return "orga/bewerbungenVerwalten";
  }


}