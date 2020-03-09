package mops.controllers.rest;

import mops.domain.models.fragebogen.Bewerber;
import mops.domain.repositories.BewerberRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class BewerberRestController {
    private BewerberRepository bewerberRepository;

    public BewerberRestController(BewerberRepository bewerberRepository){
        this.bewerberRepository = bewerberRepository;
    }

    @RequestMapping(path = "/postbewerbungrest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Bewerber index(Model model, @RequestBody Bewerber b){
        bewerberRepository.save(b);
        System.out.println("added " + b.getKennung() + " to database");
        return b;
    }
}
