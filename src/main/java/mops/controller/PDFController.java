package mops.controller;

import mops.domain.models.Bewerber;
import mops.services.BewerberService;
import mops.services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class PDFController {

  private final transient PDFService pdfService;

  private final transient BewerberService bewerberService;

  public PDFController(PDFService pdfService, BewerberService bewerberService) {
    this.pdfService = pdfService;
    this.bewerberService = bewerberService;
  }


  @GetMapping("/anzeigen")
  public String anzeigenPDF(Model m, Bewerber bewerber){
    return "pdf/pdfUebersicht";
  }
}
