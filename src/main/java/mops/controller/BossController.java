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
  private transient ModulService modulService;

  /**
   * Modul list for boss. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return moduleEinsehen html template
   */
  //@Secured("ROLE_boss")
  @GetMapping("/modules")
  public String getModule(Model m, KeycloakAuthenticationToken token) {
    m.addAttribute("modul", new Modul());
    return "boss/modulsetup";
  }

  /**
   * add Modul for boss. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @param modul Modul, added to DB
   * @return redirect to Modul list
   */
  //@Secured("ROLE_boss")
  @PostMapping("/postmodule")
  public String addModule(Model m, KeycloakAuthenticationToken token, Modul modul ) {
    modulService.addModul(modul);
    return "redirect:/bewerbung1/boss/modules";
  }

}
