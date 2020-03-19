package mops.controllers;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_VERTEILER;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.ModulDTO;
import mops.services.BewerberService;
import mops.services.ModelService;
import mops.services.ModulService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bewerbung1/verteiler")
public class VerteilerController {

  private transient BewerberService bewerberService;
  private transient ModulService modulService;

  public VerteilerController(BewerberService bewerberService, ModulService modulService) {
    this.bewerberService = bewerberService;
    this.modulService = modulService;
  }
    
  @Secured(ROLE_VERTEILER)
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    List<BewerberDTO> offeneBewerbungen = bewerberService.findNichtVerteilt();
    List<BewerberDTO> zugewieseneBewerbungen = bewerberService.findVerteilt();
    List<BewerberDTO> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());

    model.addAttribute("offeneBewerbungen", offeneBewerbungen);
    model.addAttribute("zugewieseneBewerbungen", zugewieseneBewerbungen);
    model.addAttribute("offeneBewerbungenPreview", offeneBewerbungenPreview);
    model.addAttribute("alleModule", modulService.findAllModule());
    model.addAttribute("modul", new ModulDTO());

    return "verteiler/Verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteilung")
  public String verteilDetails(Model m, @RequestParam String kennung) {
    System.out.println("print: " + kennung);
    System.out.println("=============================");
    System.out.println(bewerberService.findAlleBewerber());
    System.out.println("=============================");
    System.out.println(bewerberService.findBewerberByKennung(kennung));
    System.out.println("///////////////////////////////");
    BewerberDTO bewerber = bewerberService.findBewerberByKennung(kennung);
    m.addAttribute("bewerber", bewerber);
    m.addAttribute("verteiltan", "");
    return "verteilungsdetails";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteile")
  public String verteile(Model m, @RequestParam String bewerberKennung, ModulDTO modulDTO) {
    ModelService modelService = new ModelService();
    bewerberService.verteile(bewerberKennung, modelService.loadModul(modulDTO).getDozent());
    return "redirect:/bewerbung1/verteiler/uebersicht";
  }
}