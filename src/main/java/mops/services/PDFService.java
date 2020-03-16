package mops.services;

import jdk.tools.jlink.internal.ModularJarArchive;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.models.Karriere;
import mops.domain.models.ModulAuswahl;
import mops.domain.models.Personalien;
import mops.domain.models.Praeferenzen;
import mops.domain.repositories.BewerberRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;

public class PDFService {







    public void convertWissenHilfskraft(BewerberDTO bewerberDTO) throws IOException {
        PDDocument pDDocument = PDDocument.load(new File("/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/3_Antrag_Beschaeftigung_wiss_Hilfskraefte_mit_BA.pdf"));
        PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

        PDField field = pDAcroForm.getField("Vorname");
        field.setValue(bewerberDTO.getPersonalienDTO().getVorname());
        field = pDAcroForm.getField("Name");
        field.setValue(bewerberDTO.getPersonalienDTO().getName());
        field = pDAcroForm.getField("Geburtsdatum");
        field.setValue(bewerberDTO.getPersonalienDTO().getGeburtsdatum());
        field = pDAcroForm.getField("Staatsangehörigkeit");
        field.setValue(bewerberDTO.getPersonalienDTO().getNationalitaet());
        field = pDAcroForm.getField("Anschrift(Straße)");
        field.setValue(bewerberDTO.getPersonalienDTO().getAdresseDTO().getStraße());
        field = pDAcroForm.getField("Anschrift(Hausnummer)");
        field.setValue(bewerberDTO.getPersonalienDTO().getAdresseDTO().getHausnummer());
        field = pDAcroForm.getField("Anschrift(PLZ)");
        field.setValue(bewerberDTO.getPersonalienDTO().getAdresseDTO().getPLZ());
        field = pDAcroForm.getField("Anschrift(ORT)");
        field.setValue(bewerberDTO.getPersonalienDTO().getAdresseDTO().getWohnort());


        field = pDAcroForm.getField("Neueinstellung");
        field.setValue(bewerberDTO.getPraeferenzenDTO().getEinstiegtyp());
        field = pDAcroForm.getField("Weiterbeschäftigung");
        field.setValue(bewerberDTO.getPraeferenzenDTO().getEinstiegtyp());
        field = pDAcroForm.getField("Arbeitszeit");
        field.setValue(bewerberDTO.getPraeferenzen().getMinWunschStunden() + " - " + bewerberDTO.getPraeferenzen().getMinWunschStunden().getMaxWunschStunden());
        field = pDAcroForm.getField("Bemerkung zum Antrag");
        field.setValue(bewerberDTO.getPraeferenzenDTO().getKommentar() + "/n" + bewerberDTO.getPraeferenzenDTO().getEinschraenkungen() );




        field = pDAcroForm.getField("Immatrikulation");
        field.setValue(bewerberDTO.getKarriereDTO().getImmartikulationsStatus().getStatus());
        field = pDAcroForm.getField("Studiengang");
        field.setValue(bewerberDTO.getKarriereDTO().getImmartikulationsStatus().getFachrichtung());



    }


}
