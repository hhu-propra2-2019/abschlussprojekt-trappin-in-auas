package mops.controllers;

import java.util.List;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import mops.domain.models.fragebogen.Bewerber;
import mops.services.BewerberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verteiler")
public class VerteilerController {
    
    @Autowired
    private BewerberService bewerberService;

    @Secured({"ROLE_orga"})
    @GetMapping("/uebersicht")
    public String verteilen(Model model, KeycloakAuthenticationToken token){
        List<Bewerber> alleBewerber = bewerberService.findAllBewerber();
        System.out.println(alleBewerber);
        model.addAttribute("bewererbungen", alleBewerber);
        return "verteiler";
    }
    
}