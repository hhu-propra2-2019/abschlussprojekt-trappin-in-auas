package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.*;

import java.util.Set;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mops.services.KeycloakRoleService;

@Controller
@RequestMapping("/bewerbung1")
public class BewerbungController {

  @Autowired
  private transient KeycloakRoleService keycloakRoleService;

  /**
   * Main page, checks login for roles studentin and orga.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return redirect to specific html template or error page
   */
  @GetMapping("")
  @Secured({ROLE_ORGA, ROLE_STUDENT, ROLE_BOSS, ROLE_VERTEILER})
  public String mainpage(Model model, KeycloakAuthenticationToken token) {
    Set<String> tokenRoles = token.getAccount().getRoles();
    return "redirect:/bewerbung1"+keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles);
  }

  /**
   * Orga dashboard. Login as "orga" required.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return orgaMainpage html template
   */
  @GetMapping("/orga")
  @Secured(ROLE_ORGA)
  public String getOrgaMainpage(Model model, KeycloakAuthenticationToken token) {
    return "orga/orgaMainpage";
  }

  /**
   * Boss dashboard. Login as "boss" required.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return bossMainpage html template
   */
  @GetMapping("/boss")
  @Secured(ROLE_BOSS)
  public String getBossMainpage(Model model, KeycloakAuthenticationToken token) {
    return "boss/bossMainpage";
  }

  /**
   * verteiler dashboard. Login as "verteiler" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return verteilerMainpage html template
   */
  @GetMapping("/verteiler")
  @Secured(ROLE_VERTEILER)
  public String getVerteilerMainpage(Model m, KeycloakAuthenticationToken token) {
    return "verteiler/verteilerMainpage";
  }
}
