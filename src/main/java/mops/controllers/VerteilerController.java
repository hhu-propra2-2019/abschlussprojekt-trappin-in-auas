package mops.controllers;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.database.dto.BewerberDTO;
import mops.services.BewerberService;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verteiler")
public class VerteilerController {

  private transient BewerberService bewerberService;

    public VerteilerController(BewerberService bewerberService){
      this.bewerberService = bewerberService;
    }
    
    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht")
    public String verteilen(Model model, KeycloakAuthenticationToken token){
        //List<BewerberDTO> offeneBewerbungen = bewerberService.findNichtVerteilt();
        //List<BewerberDTO> zugewieseneBewerbungen = bewerberService.findVerteilt();
        //List<BewerberDTO> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());
        return "verteiler/verteilerMainpage";
    }
    
    @Secured({"ROLE_orga"})
    @GetMapping("/verteilteBewerbungen")
    public String verteilen(Model model, KeycloakAuthenticationToken token){
        //List<BewerberDTO> zugewieseneBewerbungen = bewerberService.findVerteilt();
        return "verteiler/verteilerMainpage";
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
    BewerberDTO bewerber = bewerberService.findBewerberByKennung(kennung);
    m.addAttribute("bewerber", bewerber);
    m.addAttribute("verteiltan", "");
    return "verteilungsdetails";
  }
}