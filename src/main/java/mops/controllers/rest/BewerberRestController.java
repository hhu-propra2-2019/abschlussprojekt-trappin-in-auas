package mops.controllers.rest;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;
import mops.services.BewerberService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BewerberRestController {
    //TODO: Crud operations for applications
    private transient BewerberRepository bewerberRepository;

    private transient IBewerberService bewerberService;

    public BewerberRestController(BewerberRepository bewerberRepository, BewerberService bewerberService){
        this.bewerberRepository = bewerberRepository;
        this.bewerberService = bewerberService;
    }

    @PostMapping(path = "/postbewerbungrest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Secured(ROLE_ORGA) //andere clientrollen kommen noch
    public BewerberDTO index(Model model, @RequestBody BewerberDTO b, KeycloakAuthenticationToken token){
        //bewerberRepository.save(b);
        System.out.println("added " + b.getKennung() + " to database");
        return b;
    }

    @GetMapping(path = "/allebewerbungen", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ROLE_ORGA)
    public List<BewerberDTO> allebewerbungen(){
        System.out.println("getting all applications");
        return bewerberService.findAlleBewerber();
    }
}
