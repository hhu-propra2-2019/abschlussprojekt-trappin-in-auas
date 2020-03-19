package mops.controllers;

import mops.domain.models.Modul;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mops.services.ModulService;

@Controller
@RequestMapping("/bewerbung1/boss")
public class ModulSetupController {

    @Autowired
    private transient ModulService modulService;

    @GetMapping("/modulsetup")
    public String modulsetup(Model m) {
        m.addAttribute("modules", modulService.findAllModule());
        m.addAttribute("modul", new Modul());
        return "boss/modulsetup";
    }

    @GetMapping("/module")
    public String getModule(Model m, KeycloakAuthenticationToken token) {
        m.addAttribute("modules", modulService.findAllModule());
        return "boss/moduleEinsehen";
    }
    @PostMapping("/postmodule")
    public String addModule(Model m, KeycloakAuthenticationToken token, Modul modul) {
        modulService.addModul(modul);
        return "redirect:./modulsetup";
    }
}