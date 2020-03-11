package mops.controller;

import mops.domain.models.lehrstuhl.Modul;
import mops.services.ModulService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bewerbung1/boss")
public class BossController {

  @Autowired
  private ModulService modulService;

  //@Secured("ROLE_boss")
  @GetMapping("/module")
  public String getModule(Model m, KeycloakAuthenticationToken token) {
    m.addAttribute("modules", modulService.findAllModule());
    return "boss/moduleEinsehen";
  }

  //@Secured("ROLE_boss")
  @PostMapping("/module")
  public String addModule(Model m, KeycloakAuthenticationToken token,
      @RequestParam String modulName) {
    modulService.addModul(new Modul(modulName));
    return "redirect:/bewerbung1/boss/module";
  }

}
