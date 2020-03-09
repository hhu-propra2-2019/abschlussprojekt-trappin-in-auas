package mops.controllers;

import mops.domain.models.fragebogen.Bewerber;
import mops.domain.repositories.BewerberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BewerberController {
    private BewerberRepository bewerberRepository;

    public BewerberController(BewerberRepository bewerberRepository){
        this.bewerberRepository = bewerberRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("bewerber", new Bewerber());
        return "bewerbungsformular";
    }

    @PostMapping("/postbewerbung")
    public String postBewerber(Model m, Bewerber b){
        bewerberRepository.save(b);
        return "redirect:/example";
    }

}
