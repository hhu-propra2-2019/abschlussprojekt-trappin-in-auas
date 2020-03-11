package mops.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.Bewerber;
import mops.services.BewerberService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verteiler")
public class VerteilerController {

  @Autowired
  private BewerberService bewerberService;

  @Secured({"ROLE_orga"})
  @GetMapping("/uebersicht")
  public String verteilen(Model model, KeycloakAuthenticationToken token) {
    List<Bewerber> offeneBewerbungen = bewerberService.findNichtVerteilt();
    List<Bewerber> zugewieseneBewerbungen = bewerberService.findVerteilt();
    List<Bewerber> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5)
        .collect(Collectors.toList());

    model.addAttribute("offenecount", offeneBewerbungen.size());
    model.addAttribute("zugewiesenecount", zugewieseneBewerbungen.size());
    model.addAttribute("preview", offeneBewerbungenPreview);
    return "orga/verteilung/uebersicht";
  }

  @Secured({"ROLE_orga"})
  @PostMapping("/verteilung")
  public String verteilDetails(Model m, @RequestParam String kennung) {
    System.out.println("print: " + kennung);
    System.out.println("=============================");
    System.out.println(bewerberService.findAlleBewerber());
    System.out.println("=============================");
    System.out.println(bewerberService.findBewerberByKennung(kennung));
    System.out.println("///////////////////////////////");
    Bewerber bewerber = bewerberService.findBewerberByKennung(kennung);
    m.addAttribute("bewerber", bewerber);
    m.addAttribute("verteiltan", "");
    return "verteilungsdetails";
  }

  @Secured({"ROLE_orga"})
  @PostMapping("/verteilung/{kennung}")
  public String verteile(Model m, @PathVariable("kennung") String kennung,
      @RequestParam String verteiltan) {
    System.out.println(verteiltan);
    System.out.println(kennung);
    bewerberService.verteile(kennung, verteiltan);
    return "redirect:/verteiler/uebersicht";
  }

}