package mops.controllers.rest;

import mops.domain.database.models.Bewerber;
import mops.domain.repositories.BewerberRepository;

import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.authentication.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BewerberRestController {
    private BewerberRepository bewerberRepository;

    public BewerberRestController(BewerberRepository bewerberRepository){
        this.bewerberRepository = bewerberRepository;
    }

    @PostMapping(path = "/postbewerbungrest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Secured({"ROLE_orga"}) //andere clientrollen kommen noch
    public Bewerber index(Model model, @RequestBody Bewerber b, KeycloakAuthenticationToken token){
        //bewerberRepository.save(b);
        System.out.println("added " + b.getKennung() + " to database");
        return b;
    }
}
