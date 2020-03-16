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

  private transient BewerberRepository bewerberRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("bewerber", new BewerberDTO());
        return "bewerbungsformular";
    }

    @GetMapping("/bewirb")
    public String bewirb(Model model){
        model.addAttribute("bewerber", new Bewerber());
        return "student/main_min";
    }

    @PostMapping("/bewirbabschicken")
    public String bewirbabschicken(Model model, Bewerber bewerber){
        System.out.println("form abgeschickt. folgende inhalte:");
        System.out.println(bewerber);
        return "redirect:/bewirb";
    }

    @PostMapping("/postbewerbung")
    public String postBewerber(Model m, BewerberDTO b){
        bewerberRepository.save(b);
        return "redirect:/example";
    }

}
