package mops.controller;

import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_ORGA;
import static mops.authentication.account.keycloak.KeycloakRoles.ROLE_VERTEILER;

import org.springframework.http.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.servlet.http.HttpServletResponse;
import mops.domain.models.Bewerber;
import mops.services.BewerberService;
import mops.services.PDFService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  /**
   * delete module. Login as "Boss" required.
   * @return starts Download
   */
  @PostMapping(value = "/download/{kennung}" , produces = MediaType.APPLICATION_PDF_VALUE)
  @Secured({ROLE_VERTEILER,ROLE_ORGA})
  @ResponseBody
  public InputStreamResource downloadPDF(@PathVariable("kennung") String kennung,
      HttpServletResponse response) throws FileNotFoundException {

    Bewerber bewerber = pdfService.getBewerber(kennung);
    String fileDirectory = pdfService.fileDirectory(bewerber);
    String path = pdfService.fillPDF(bewerber,fileDirectory);
    
    String fileName = path.replace("./src/main/resources/static/output//", "");

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=\"output_studentin_Studentin.pdf\"");
    return new InputStreamResource(new FileInputStream(path));
  }

}
