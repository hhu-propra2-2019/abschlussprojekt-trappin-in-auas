package mops.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Set;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bewerbung1")
public class BewerbungController {

  private final Counter authenticatedAccess;
  private final Counter publicAccess;

  public BewerbungController(MeterRegistry registry) {
    authenticatedAccess = registry.counter("access.authenticated");
    publicAccess = registry.counter("access.public");
  }

  /**
   * Nimmt das Authentifizierungstoken von Keycloak und erzeugt ein AccountDTO für die Views.
   *
   * @param token Token von Keycloak
   * @return neuen Account der im Template verwendet wird
   */
  private Account createAccountFromPrincipal(KeycloakAuthenticationToken token) {
    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    return new Account(
        principal.getName(),
        principal.getKeycloakSecurityContext().getIdToken().getEmail(),
        null,
        token.getAccount().getRoles());
  }

  @GetMapping("/")
  @Secured({"ROLE_studentin", "ROLE_orga"})
  public String mainpage(Model model, KeycloakAuthenticationToken token) {
    Set tokenRole = token.getAccount().getRoles();

    if (tokenRole.contains("studentin")) {
      return "studentMainpage";
    } else if (tokenRole.contains("orga")) {
      return "orgaMainpage";
    } else {
      return "falscheRolle";
    }

  }
}
