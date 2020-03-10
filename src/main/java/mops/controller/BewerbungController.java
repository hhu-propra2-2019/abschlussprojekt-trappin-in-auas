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

  @GetMapping("/student")
  @Secured("ROLE_studentin")
  public String getStudentMainpage(Model model, KeycloakAuthenticationToken token) {
    return "Student/studentMainpage";
  }

  @GetMapping("/orga")
  @Secured("ROLE_orga")
  public String getOrgaMainpage(Model model, KeycloakAuthenticationToken token) {
    return "orga/orgaMainpage";
  }


}
