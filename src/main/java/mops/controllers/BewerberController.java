package mops.controllers;

import mops.domain.database.dto.*;
import mops.domain.models.*;
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

    @GetMapping("/bewirb")
    public String bewirb(Model model){
        model.addAttribute("personal", new Personalien());
        model.addAttribute("modulauswahl", new ModulAuswahl());
        return "student/main_min";
    }

    @PostMapping("/bewirbabschicken")
    public String bewirbabschicken(Model model, Personalien p, ModulAuswahl m){
        System.out.println("form abgeschickt. folgende inhalte:");
        System.out.println(p);
        System.out.println(m);
        return "redirect:/bewirb";
    }

    @PostMapping("/postbewerbung")
    public String postBewerber(Model m, Bewerber b){
        bewerberRepository.save(b);
        return "redirect:/example";
    }

}
