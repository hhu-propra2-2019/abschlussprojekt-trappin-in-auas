package mops.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModulSetupController {

    @GetMapping("/modulsetup")
    public String modulsetup(Model m){
        return "setup/modulsetup";
    }
}