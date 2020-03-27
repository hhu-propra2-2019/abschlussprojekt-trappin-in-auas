package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_BOSS;

import mops.services.*;
import mops.domain.models.Modul;
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
@RequestMapping("/bewerbung1/setup")
public class BossController {

  @Autowired
  private transient ModulService modulService;

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient VerteilerService verteilerService;

  @Autowired
  private transient ZyklusDirigentService zyklusDirigentService;

  /**
   * Modul list for boss. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return modulesetup html template
   */
  @Secured(ROLE_BOSS)
  @GetMapping("")
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
    modulService.addModul(modul);
    return "redirect:/bewerbung1/setup/";
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
    modulService.deleteModulByName(modulName);
    return "redirect:/bewerbung1/setup/";
  }
  
  /**
   * stopover page to prevent accidental modul wipe
   * here the user is promped to confirm his wipe or to go back
   * @param token injected, present, if user is logged in
   * @return confirm wipe page
   */
  @Secured(ROLE_BOSS)
  @GetMapping("/confirmclear")
  public String confirmClearPage(KeycloakAuthenticationToken token){
    return "boss/confirmclear";
  }

  /**
   * delete all module. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @return redirect to modules
   */
  @Secured(ROLE_BOSS)
  @PostMapping("/deleteall")
  public String wipeAll(Model m, KeycloakAuthenticationToken token) {
    verteilerService.removeAll();
    bewerberService.removeAll();
    modulService.deleteAll();
    zyklusDirigentService.entfernen();
    return "redirect:/bewerbung1/setup/";
  }
}