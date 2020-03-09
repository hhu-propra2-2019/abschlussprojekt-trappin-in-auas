package mops.controllers;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.database.models.Bewerber;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import mops.services.BewerberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dozent") //this site is specific to the logged in dozent
public class DozentController {
    
    @Autowired
    private BewerberService bewerberService;

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht")
    public String verteilen(Model model, KeycloakAuthenticationToken token){
        List<Bewerber> alleBewerber = bewerberService.findAlleBewerber();
        List<Bewerber> offeneBewerbungen = bewerberService.findAlleNichtVerteilteBewerber(alleBewerber);
        List<Bewerber> zugewieseneBewerbungen = bewerberService.findAlleVerteilteBewerber(alleBewerber);
        List<Bewerber> offeneBewerbungenPreview = offeneBewerbungen.stream().limit(5).collect(Collectors.toList());

        model.addAttribute("bewererbungen", alleBewerber);
        return "verteiler";
    }
}