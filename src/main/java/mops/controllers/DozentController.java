package mops.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import mops.domain.database.dto.BewerberDTO;
import mops.services.BewerberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dozent") //this site is specific to the logged in dozent
public class DozentController {
    
    @Autowired
    private BewerberService bewerberService;

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht")
    public String verteilen(Model model, KeycloakAuthenticationToken token){
        List<BewerberDTO> alleBewerber = bewerberService.findAlleBewerber();
        List<BewerberDTO> offeneBewerbungen = bewerberService.findNichtVerteilt();
        List<BewerberDTO> zugewieseneBewerbungen = bewerberService.findVerteilt();
        List<BewerberDTO> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());

        model.addAttribute("bewerbungen", alleBewerber);
        model.addAttribute("offenecount", offeneBewerbungen.size());
        model.addAttribute("zugewiesenecount", zugewieseneBewerbungen.size());
        model.addAttribute("preview", offeneBewerbungenPreview);

        return "dozent/uebersicht";
    }

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht/offene")
    public String offeneUebersicht(Model model, KeycloakAuthenticationToken token){
        List<BewerberDTO> offeneBewerbungen = bewerberService.findNichtVerteilt();

        model.addAttribute("offene", offeneBewerbungen);

        return "";
    }

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht/zugewiesene")
    public String zugewieseneUebersicht(Model model, KeycloakAuthenticationToken token){
        List<BewerberDTO> zugewiesene = bewerberService.findVerteilt();

        model.addAttribute(zugewiesene);

        return "";
    }

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht/detail/{kennung}")
    public String detailAnsicht(Model model, KeycloakAuthenticationToken token, @PathVariable String kennung){
        BewerberDTO bewerber = bewerberService.findBewerberByKennung(kennung);

        model.addAttribute("bewerber", bewerber);
        return "";
    }
}