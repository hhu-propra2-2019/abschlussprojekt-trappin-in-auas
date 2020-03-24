package mops.controller;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.services.BewerberService;
import mops.services.ModulService;

import java.util.ArrayList;
import java.util.List;

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

  @GetMapping("")
  public String index(Model model) {
    return "student/bewerberuebersicht";
  }

  @GetMapping("/bewerbung")
  @Secured({ "ROLE_studentin" })
  public String bewirb(Model model, KeycloakAuthenticationToken token) {
    model.addAttribute("bewerber", bewerberService.initialiseBewerber());
    model.addAttribute("existingmodule", modulService.findAllModule());
    return "student/main_min";
  }

  @PostMapping("/bewerbungabschicken")
  @Secured({ "ROLE_studentin" })
  public String bewirbabschicken(Model model, Bewerber bewerber, KeycloakAuthenticationToken token) {
    bewerberService.addBewerber(bewerber, token.getName());
    return "redirect:./bewerbung";
  }
}
