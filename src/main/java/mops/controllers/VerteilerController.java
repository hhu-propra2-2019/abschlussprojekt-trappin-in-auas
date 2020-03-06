package mops.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import mops.services.BewerberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerteilerController {
    
    @Autowired
    private BewerberService bewerberService;

    @GetMapping("/verteiler")
    public String verteilen(Model model){
        return "";
    }
    
}