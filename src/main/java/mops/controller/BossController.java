package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_BOSS;

import mops.services.DTOService;
import mops.domain.models.Modul;
import mops.services.ModelService;
import mops.services.ModulService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

  @Autowired
  private transient ModelService mappingService;

  @Autowired
  private transient DTOService dtoService;

  /**
   * Modul list for boss. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return modulesetup html template
   */
  @Secured(ROLE_BOSS)
  @GetMapping("/modules")
  public String getModule(Model m, KeycloakAuthenticationToken token) {
    m.addAttribute("modul", new Modul());
    m.addAttribute("modulListe", modulService.findAllModule());
    return "boss/modulsetup";
  }

  /**
   * add Modul for boss. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @param modul Modul, added to DB
   * @return redirect to modules
   */
  @Secured(ROLE_BOSS)
  @PostMapping("/postmodule")
  public String addModule(Model m, KeycloakAuthenticationToken token, Modul modul) {
    modulService.addModul(dtoService.load(modul));
    return "redirect:/bewerbung1/boss/modules";
  }

  /**
   * delete module. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @param modulName String, for delete query
   * @return redirect to modules
   */
  @Secured(ROLE_BOSS)
  @PostMapping("/delete")
  public String deleteModule(Model m, KeycloakAuthenticationToken token,
      @RequestParam String modulName) {
    System.out.println(modulName);
    modulService.deleteModulByName(modulName);
    return "redirect:/bewerbung1/boss/modules";
  }
}