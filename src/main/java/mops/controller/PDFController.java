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

@Controller
@RequestMapping("/pdf")
public class PDFController {

  private final transient PDFService pdfService;

  private final transient BewerberService bewerberService;

  public PDFController(PDFService pdfService, BewerberService bewerberService) {
    this.pdfService = pdfService;
    this.bewerberService = bewerberService;
  }


  @RequestMapping(value = "/download", method = RequestMethod.GET)
  @Secured(ROLE_VERTEILER)
  public FileSystemResource downloadPDF(Model m, Bewerber bewerber, KeycloakAuthenticationToken token){

    String filePath = pdfService.fileDirectory(bewerber);
    pdfService.fillPDF(bewerber,filePath);
    return new FileSystemResource(filePath);
    //return "wiss_Hilfskraft";
  }
}
