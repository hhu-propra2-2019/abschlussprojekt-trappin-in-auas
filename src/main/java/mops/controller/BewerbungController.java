package mops.controller;

import java.util.Set;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bewerbung1")
public class BewerbungController {

  /**
   * Main page, checks login for roles studentin and orga.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return redirect to specific html template or error page
   */
  @GetMapping("")
  @Secured({"ROLE_studentin", "ROLE_orga"})
  public String mainpage(Model model, KeycloakAuthenticationToken token) {
    Set<String> tokenRole = token.getAccount().getRoles();

    if (tokenRole.contains("studentin")) {
      return "redirect:/bewerbung1/student";
    } else if (tokenRole.contains("orga")) {
      return "redirect:/bewerbung1/orga";
    } else {
      return "falscheRolle";
    }
  }

  /**
   * Students dashboard. Login as "studentin" required.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return studentMainpage html template
   */
  @GetMapping("/student")
  @Secured("ROLE_studentin")
  public String getStudentMainpage(Model model, KeycloakAuthenticationToken token) {
    return "student/studentMainpage";
  }

  /**
   * Orga dashboard. Login as "orga" required.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return orgaMainpage html template
   */
  @GetMapping("/orga")
  @Secured("ROLE_orga")
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
  //@Secured("ROLE_Boss")
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
  //@Secured("ROLE_verteiler")
  public String getVerteilerMainpage(Model m, KeycloakAuthenticationToken token) {
    return "verteiler/verteilerMainpage";
  }
}
