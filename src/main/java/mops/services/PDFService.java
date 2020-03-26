package mops.services;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.stereotype.Service;

import mops.domain.models.Bewerber;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.ModulAuswahl;
import mops.domain.services.IPDFService;

@SuppressWarnings("PMD.UnusedLocalVariable")
@Service
public class PDFService implements IPDFService {
  private static final String PDF_PATH = "./src/main/resources/static/";

  public String fileDirectory(Bewerber bewerber) {
    if (bewerber.getKarriere().getFachAbschluss() == null) {
      return PDF_PATH + "studentische_Hilfskraft.pdf";
    } else {
      return PDF_PATH + "wissenschaftliche_Hilfskraft.pdf";
    }
  }

  public void checkEncryption(PDDocument pdDocument) throws Exception {
    if (pdDocument.isEncrypted()) {
      try {
        pdDocument.setAllSecurityToBeRemoved(true);
      } catch (Exception e) {
        throw new Exception("The document is encrypted, and we can't decrypt it.", e);
      }
    }
  }

  @SuppressWarnings("PMD.CloseResource")
  public void fillPDF(Bewerber bewerber, String file) throws Exception {
    PDDocument pDDocument = PDDocument.load(new File(file));
    System.out.println("PDF geladen");
    try {
      System.out.println("versuche zu fuellen");
      checkEncryption(pDDocument);
      loadPDFFelder(bewerber, pDDocument);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (hatAbschluss(bewerber)) {
        pDDocument.save(PDF_PATH + "/output/output_" + bewerber.getPersonalien().getName() + ".pdf");
      } else {
        pDDocument.save(PDF_PATH + "/output/output.pdf");
      }
      pDDocument.close();
    }
  }

  public void loadPDFFelder(Bewerber bewerber, PDDocument pdDocument) throws Exception {
    PDAcroForm pDAcroForm = pdDocument.getDocumentCatalog().getAcroForm();

    PDField field = pDAcroForm.getField("Vorname");
    field.setValue(bewerber.getPersonalien().getVorname());
    System.out.println(field);

    System.out.println(field);

    field = pDAcroForm.getField("Geburtsdatum");
    field.setValue(bewerber.getPersonalien().getGeburtsdatum().toString());
    System.out.println(field);

    field = pDAcroForm.getField("Staatsangehörigkeit");
    field.setValue(bewerber.getPersonalien().getNationalitaet());
    System.out.println(field);
    field = pDAcroForm.getField("Geburtsort");
    field.setValue(bewerber.getPersonalien().getGeburtsort());

    field = pDAcroForm.getField("Anschrift (Straße)");
    field.setValue(bewerber.getPersonalien().getAdresse().getStrasse());
    field = pDAcroForm.getField("Anschrift (Hausnummer)");
    field.setValue(bewerber.getPersonalien().getAdresse().getHausnummer());
    field = pDAcroForm.getField("Anschrift (PLZ)");
    field.setValue(bewerber.getPersonalien().getAdresse().getPLZ());
    System.out.println(field);
    field = pDAcroForm.getField("Anschrift (Ort)");
    field.setValue(bewerber.getPersonalien().getAdresse().getWohnOrt());
    System.out.println(field);
    field = pDAcroForm.getField("Vertragsart");
    field.setValue(pruefeVertragsart(bewerber));

    field = pDAcroForm.getField("Stunden");
    field.setValue(
        bewerber.getPraeferenzen().getMinWunschStunden() + " - " + bewerber.getPraeferenzen().getMaxWunschStunden());
    field = pDAcroForm.getField("Immatrikulation");
    field.setValue(pruefeStatus(bewerber));
    field = pDAcroForm.getField("Studiengang");
    field.setValue(bewerber.getKarriere().getImmartikulationsStatus().getFachrichtung());

    field = pDAcroForm.getField("Bemerkung zum Antrag");
    field.setValue("On");
    field = pDAcroForm.getField("Bemerkung zum Antrag1");
    field.setValue(bewerber.getKarriere().getArbeitserfahrung() + "/n" + bewerber.getPraeferenzen().getKommentar()
        + "/n" + bewerber.getPraeferenzen().getEinschraenkungen());

    if (hatAbschluss(bewerber)) {
      field = pDAcroForm.getField("Schilderung der auszuübenden Tätigkeit");
      field.setValue(berufModul(bewerber));
    }
  }

  public boolean hatAbschluss(Bewerber bewerber) {
    return bewerber.getKarriere().getFachAbschluss() != null;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public String berufModul(Bewerber bewerber) {
    String berufModul = "";
    for (ModulAuswahl modulAuswahl : bewerber.getPraeferenzen().getModulAuswahl()) {
      berufModul = berufModul + " " + modulAuswahl.getModul().getModulName() + ": " + modulAuswahl.getBeruf().toString()
          + "\n";
    }
    return berufModul;
  }

  public String pruefeStatus(Bewerber bewerber) {
    if (bewerber.getKarriere().getImmartikulationsStatus().isStatus()) {
      return "On";
    }
    return "Off";
  }

  public String pruefeVertragsart(Bewerber bewerber) {
    if (bewerber.getPraeferenzen().getEinstiegTyp() == EinstiegTyp.WEITERBESCHAEFTIGUNG) {
      return "Weiterbeschäftigung";
    } else if (bewerber.getPraeferenzen().getEinstiegTyp() == EinstiegTyp.NEUEINSTIEG) {
      return "Einstellung";
    } else {
      return "Off";
    }
  }
}