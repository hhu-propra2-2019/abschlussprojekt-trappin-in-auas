package mops.controller;

import java.util.Set;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/bewerbung1")
@PropertySource("classpath:roles.properties")
public class BewerbungController {

  @Value("${role.orga:ROLE_orga}")
  private String ROLE_ORGA;
  @Value("${role.studentin}")
  private String ROLE_STUDENT;
  @Value("${role.boss}")
  private String role_boss_injected;
  @Value("${role.verteiler}")
  private String role_verteiler_injected;

  /**
   * Main page, checks login for roles studentin and orga.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return redirect to specific html template or error page
   */
  @GetMapping("")
  @Secured({ROLE_ORGA, ROLE_STUDENT})
  //@RolesAllowed({ROLE_ORGA, ROLE_STUDENT})
  @PreAuthorize("hasRole('ROLE_STUDENT')")
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
  //@Secured(ROLE_STUDENT)
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
  //@Secured(ROLE_ORGA)
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
  //@Secured(ROLE_BOSS)
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
  //@Secured(ROLE_VERTEILER)
  public String getVerteilerMainpage(Model m, KeycloakAuthenticationToken token) {
    return "verteiler/verteilerMainpage";
  }
}
