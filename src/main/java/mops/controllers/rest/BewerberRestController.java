package mops.controllers.rest;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;
import mops.services.BewerberService;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BewerberRestController {
    //TODO: Crud operations for applications
    private BewerberRepository bewerberRepository;

    private IBewerberService bewerberService;

    public BewerberRestController(BewerberRepository bewerberRepository, BewerberService bewerberService){
        this.bewerberRepository = bewerberRepository;
        this.bewerberService = bewerberService;
    }

    @PostMapping(path = "/postbewerbungrest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Secured({"ROLE_orga"}) //andere clientrollen kommen noch
    public BewerberDTO index(Model model, @RequestBody BewerberDTO b, KeycloakAuthenticationToken token){
        //bewerberRepository.save(b);
        System.out.println("added " + b.getKennung() + " to database");
        return b;
    }

    @GetMapping(path = "/allebewerbungen", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_orga"})
    public List<BewerberDTO> allebewerbungen(){
        System.out.println("getting all applications");
        return bewerberService.findAlleBewerber();
    }
}
