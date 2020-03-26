package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_VERTEILER;

import javax.annotation.security.RolesAllowed;
import mops.domain.models.Bewerber;
import mops.services.BewerberService;
import mops.services.PDFService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pdf")
public class PDFController {

  private final transient PDFService pdfService;

  private final transient BewerberService bewerberService;

  public PDFController(PDFService pdfService, BewerberService bewerberService) {
    this.pdfService = pdfService;
    this.bewerberService = bewerberService;
  }

  //TODO -Bewerber filtern(vlt per Kennung), - Form im Verteiler hinzufuegen(soll Button sein mit redirect an /pdf/downlaod)

  /**
   * delete module. Login as "Boss" required.
   * @param m injected, Model for Thymeleaf interaction
   * @param token injected, present, if user is logged in
   * @param bewerber needed for PDFoutput
   * @return starts Download
   */
  @RequestMapping(value = "/download", method = RequestMethod.POST)
  @Secured(ROLE_VERTEILER)
  @ResponseBody
  public FileSystemResource downloadPDF(Model m, Bewerber bewerber, KeycloakAuthenticationToken token){
    //bewerber filtern
    String filePath = pdfService.fileDirectory(bewerber);
    pdfService.fillPDF(bewerber,filePath);
    return new FileSystemResource(filePath);

  }
  //TODO pdf-templates können eigentlich entfernt werden , einige TODOs in einem template
  //PDF muss auch nicht mehr embedded werden.
  //PDF muss nicht mehr angezeigt werden, da Download möglich ist
  @GetMapping("/anzeigen")
  public String showPDF(Model m){
    return "pdfUebersicht";
  }
}
