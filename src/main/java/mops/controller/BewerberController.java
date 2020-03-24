package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.*;

import mops.domain.models.*;
import mops.services.BewerberService;
import mops.services.ModulService;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("")
@RequestMapping("/bewerbung1/bewerber")
public class BewerberController {

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient ModulService modulService;

  /**
   * Students dashboard. Login as "studentin" required.
   * @param model injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return studentMainpage html template
   */
  @GetMapping("")
  @Secured({ ROLE_STUDENT })
  public String index(Model model, KeycloakAuthenticationToken token) {
    model.addAttribute("bewerbungExists", bewerberService.bewerbungExists(token.getName()));
    return "student/bewerberuebersicht";
  }

  @GetMapping("/bewerbung")
  @Secured({ ROLE_STUDENT })
  public String bewirb(Model model, KeycloakAuthenticationToken token) {
    model.addAttribute("bewerber", bewerberService.initialiseBewerber());
    model.addAttribute("existingmodule", modulService.findAllModule());
    return "student/main_min";
  }

  @GetMapping("/editieren")
  @Secured({ ROLE_STUDENT })
  public String editieren(Model model, KeycloakAuthenticationToken token) {
    model.addAttribute("bewerber", bewerberService.findBewerberModelByKennung(token.getName()));
    model.addAttribute("existingmodule", modulService.findAllModule());
    return "student/main_min";
  }

  @PostMapping("/bewerbungabschicken")
  @Secured({ ROLE_STUDENT })
  public String bewirbabschicken(Model model, Bewerber bewerber, KeycloakAuthenticationToken token) {
    bewerberService.addBewerber(bewerber, token.getName());
    return "redirect:./";
  }
}
