package mops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mops.domain.models.Bewerber;
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

    @GetMapping("/uebersicht")
    public String verteilen(Model model){
        List<Bewerber> alleBewerber = bewerberService.findAllBewerber();
        System.out.println(alleBewerber);
        model.addAttribute("bewererbungen", alleBewerber);
        return "verteiler";
    }
    
}