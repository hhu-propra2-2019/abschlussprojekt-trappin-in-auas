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

@Controller
public class BewerberController {

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient ModulService modulService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("bewerber", new Bewerber());
    return "bewerbungsformular";
  }

  @GetMapping("/bewirb")
  @Secured({ "ROLE_studentin" })
  public String bewirb(Model model, KeycloakAuthenticationToken token) {
    Bewerber b = new Bewerber(new Karriere(), new Personalien(), new Praeferenzen(), token.getName(), null);
    b.getPraeferenzen().setModulAuswahl(new ArrayList<>()); // avoid list beeing null errors
    b.getPraeferenzen().getModulAuswahl().add(new ModulAuswahl());

    model.addAttribute("bewerber", b);
    model.addAttribute("existingmodule", modulService.findAllModule());
    return "student/main_min";
  }

  @PostMapping("/addModul")
  @Secured({ "ROLE_studentin" })
  public String addModul(Model m, Bewerber b, KeycloakAuthenticationToken token) {
    List<ModulAuswahl> modulauswahl = b.getPraeferenzen().getModulAuswahl();
    if (modulauswahl == null) {
      modulauswahl = new ArrayList<>();
    }
    modulauswahl.add(new ModulAuswahl());
    b.getPraeferenzen().setModulAuswahl(modulauswahl);
    b.setErstelltVon(token.getName());
    m.addAttribute("bewerber", b);
    System.out.println(b.getPraeferenzen().getModulAuswahl());
    return "student/main_min";
  }

  @PostMapping("/bewirbabschicken")
  @Secured({ "ROLE_studentin" })
  public String bewirbabschicken(Model model, Bewerber bewerber, KeycloakAuthenticationToken token) {
    bewerber.setErstelltVon(token.getName());
    System.out.println("Form abgeschickt:");
    System.out.println(bewerber);
    bewerberService.addBewerber(bewerber);
    return "redirect:/bewirb";
  }

  @PostMapping("/postbewerbung")
  public String postBewerber(Model m, BewerberDTO b) {
    // bewerberRepository.save(b);
    return "redirect:/example";
  }

}