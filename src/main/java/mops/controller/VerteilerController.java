package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_VERTEILER;

import mops.domain.models.Bewerber;
import mops.services.BewerberService;
import mops.services.DozentPraeferenzService;
import mops.services.ModelService;
import mops.services.ModulService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bewerbung1/verteiler")
public class VerteilerController {

  private transient BewerberService bewerberService;
  private transient ModulService modulService;
  private transient ModelService modelService;
  private transient DozentPraeferenzService dozentPraeferenzService;

  public VerteilerController(BewerberService bewerberService, ModulService modulService, ModelService modelService,
                             DozentPraeferenzService dozentPraeferenzService) {
    this.bewerberService = bewerberService;
    this.modulService = modulService;
    this.modelService = modelService;
    this.dozentPraeferenzService = dozentPraeferenzService;
  }
    
  @Secured(ROLE_VERTEILER)
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> offeneBewerbungen = bewerberService.findNichtVerteilt();
    List<Bewerber> zugewieseneBewerbungen = bewerberService.findVerteilt();
    List<Bewerber> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());

    model.addAttribute("anzahlOffeneBewerbungen", offeneBewerbungen.size());
    model.addAttribute("anzahlZugewieseneBewerbungen", zugewieseneBewerbungen.size());
    model.addAttribute("anzuzeigende", offeneBewerbungenPreview);

    model.addAttribute("alleModule", modulService.findAllModule());

    model.addAttribute("anzeigeModus", "uebersicht");

    return "verteiler/verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @GetMapping("/verteilte")
  public String showVerteilteBewerber(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> offeneBewerbungen = bewerberService.findNichtVerteilt();
    List<Bewerber> zugewieseneBewerbungen = bewerberService.findVerteilt();

    model.addAttribute("anzahlOffeneBewerbungen", offeneBewerbungen.size());
    model.addAttribute("anzahlZugewieseneBewerbungen", zugewieseneBewerbungen.size());
    model.addAttribute("anzuzeigende", zugewieseneBewerbungen);

    model.addAttribute("alleModule", modulService.findAllModule());

    model.addAttribute("anzeigeModus", "verteilte");

    return "verteiler/verteiler";
  }


  @Secured(ROLE_VERTEILER)
  @GetMapping("/offene")
  public String showOffeneBewerber(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> offeneBewerbungen = bewerberService.findNichtVerteilt();
    List<Bewerber> zugewieseneBewerbungen = bewerberService.findVerteilt();

    model.addAttribute("anzahlOffeneBewerbungen", offeneBewerbungen.size());
    model.addAttribute("anzahlZugewieseneBewerbungen", zugewieseneBewerbungen.size());
    model.addAttribute("anzuzeigende", offeneBewerbungen);

    model.addAttribute("alleModule", modulService.findAllModule());

    model.addAttribute("anzeigeModus", "offene");

    return "verteiler/verteiler";
  }

  @Secured(ROLE_VERTEILER)
  @GetMapping("/details/{kennung}")
  public String detailansicht(Model model, @PathVariable String kennung) {
    model.addAttribute("bewerber", bewerberService.findBewerberByKennung(kennung));
    return "bewerbungsdetails/details";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteile")
  public String verteile(Model m, @RequestParam String bewerberKennung, @RequestParam String modulName) {
    //ModelService modelService = new ModelService();
    System.out.println(modulName);
    bewerberService.verteile(bewerberKennung, modulService.findModulByModulName(modulName).getDozent());
    return "redirect:/bewerbung1/verteiler/uebersicht";
  }

  @Secured(ROLE_VERTEILER)
  @PostMapping("/verteilungentfernen")
  public String verteilungEntfernen(Model m, String bewerber, String dozentMail) {
    dozentPraeferenzService.deletePraeferenz(bewerber, dozentMail);
    return "redirect:/bewerbung1/verteiler/uebersicht/verteilte";
  }
}