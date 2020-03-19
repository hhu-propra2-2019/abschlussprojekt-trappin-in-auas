package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_STUDENT;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bewerbung1/student")
public class StudentController {


  @GetMapping("/bewerbung")
  @Secured(ROLE_STUDENT)
  public String getBewerbungsformular(Model model, KeycloakAuthenticationToken token) {
    return "student/Bewerbung";
  }

}
