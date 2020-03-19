package mops.controllers;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.services.BewerberService;

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
    System.out.println(b);
    model.addAttribute("bewerber", b);
    return "student/main_min";
  }

  @GetMapping("/verteil")
  @Secured({ "ROLE_orga" })
  public String verteil(Model model, KeycloakAuthenticationToken token) {
    Bewerber b = new Bewerber(new Karriere(), new Personalien(), new Praeferenzen(), token.getName(), null);
    b.getPraeferenzen().setModulAuswahl(new ArrayList<>()); // avoid list beeing null errors
    b.getPraeferenzen().getModulAuswahl().add(new ModulAuswahl());
    System.out.println(b);
    model.addAttribute("bewerber", b);
    return "student/main_min";
  }

  @PostMapping("/addModul")
  public String addModul(Model m, Bewerber b) {
    List<ModulAuswahl> modulauswahl = b.getPraeferenzen().getModulAuswahl();
    if (modulauswahl == null) {
      modulauswahl = new ArrayList<>();
    }
    modulauswahl.add(new ModulAuswahl());
    b.getPraeferenzen().setModulAuswahl(modulauswahl);
    m.addAttribute("bewerber", b);
    System.out.println(b.getPraeferenzen().getModulAuswahl());
    return "student/main_min";
  }

  @PostMapping("/bewirbabschicken")
  @Secured({ "ROLE_studentin" })
  public String bewirbabschicken(Model model, Bewerber bewerber, KeycloakAuthenticationToken token) {
    bewerber.getPraeferenzen().setBerufModul(
        new BerufModul(Beruf.KorrektorUndTutor, new Modul("sample", new Dozent("sample@hhu.de", "sampleDozent"))));
    System.out.println("form abgeschickt. folgende inhalte:");
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
