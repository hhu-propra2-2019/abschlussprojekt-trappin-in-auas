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







    public void fillStudentHilfskraft(BewerberDTO bewerberDTO) throws Exception {


        try {
            PDDocument pDDocument = PDDocument.load(new File("/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/321_Antrag_Beschaeftigung_stud_Hilfskraefte.pdf"));


            if (pDDocument.isEncrypted()) {
                try {
                    pDDocument.setAllSecurityToBeRemoved(true);
                } catch (Exception e) {
                    throw new Exception("The document is encrypted, and we can't decrypt it.", e);
                }
            }


            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

           /* for (PDField field2 : pDAcroForm.getFields()){
                System.out.println(field2.getFullyQualifiedName());
            }*/ //spuckt alle titelfelder aus


            //befuellen der datei
            PDField field = pDAcroForm.getField("Vorname");
            field.setValue(bewerberDTO.getPersonalien().getVorname());
            field = pDAcroForm.getField("Name");
            field.setValue(bewerberDTO.getPersonalien().getName());
            field = pDAcroForm.getField("Geburtsdatum");
            field.setValue(bewerberDTO.getPersonalien().getGeburtsdatum().toString());
            field = pDAcroForm.getField("Staatsangehörigkeit");
            field.setValue(bewerberDTO.getPersonalien().getNationalitaet());
            field = pDAcroForm.getField("Anschrift (Straße)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getStraße());
            field = pDAcroForm.getField("Anschrift (Hausnummer)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getHausnummer());
            field = pDAcroForm.getField("Anschrift (PLZ)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getPLZ());
            field = pDAcroForm.getField("Anschrift (ORT)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getWohnort());


            /*field = pDAcroForm.getField("Neueinstellung");
            field.setValue(bewerberDTO.getPraeferenzen().getEinstiegTyp().toString());*/  //das feld neueinstellung hat einen anderen namen, leider weiß ich nicht welchen
            /*field = pDAcroForm.getField("Weiterbeschäftigung"); / //anderer Name, muss nachschauen wie das Feld richtig heißt
            field.setValue(bewerberDTO.getPraeferenzen().getEinstiegTyp().toString()); */


            field = pDAcroForm.getField("Stunden");
            field.setValue(bewerberDTO.getPraeferenzen().getMinWunschStunden() + " - " + bewerberDTO.getPraeferenzen().getMaxWunschStunden());
            field = pDAcroForm.getField("Immatrikulation");
            field.setValue(pruefeStatus(bewerberDTO));
            field = pDAcroForm.getField("Studiengang");
            field.setValue(bewerberDTO.getKarriere().getImmartikulationsStatus().getFachrichtung());

            field = pDAcroForm.getField("Bemerkung zum Antrag");
            field.setValue("On");
            field = pDAcroForm.getField("Bemerkung zum Antrag1");
            field.setValue(bewerberDTO.getKarriere().getArbeitserfahrung() + "/n" + bewerberDTO.getPraeferenzen().getKommentar() + "/n" + bewerberDTO.getPraeferenzen().getEinschraenkungen());

            //Speichern der Datei
            pDDocument.save("/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/output.pdf");
            pDDocument.close();

        } catch (Exception e) {

            e.printStackTrace();

        }



    }


    public void fillWissenHilfskraft(BewerberDTO bewerberDTO) throws Exception {

        try {
            PDDocument pDDocument = PDDocument.load(new File("/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/323_Antrag_Beschaeftigung_wiss_Hilfskraefte_mit_BA.pdf"));


            if (pDDocument.isEncrypted()) {
                try {
                    pDDocument.setAllSecurityToBeRemoved(true);
                } catch (Exception e) {
                    throw new Exception("The document is encrypted, and we can't decrypt it.", e);
                }
            }
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

           /* for (PDField field2 : pDAcroForm.getFields()){
                System.out.println(field2.getFullyQualifiedName());
            }*/ //spuckt alle titelfelder aus


            //befuellen der Datei
            PDField field = pDAcroForm.getField("Vorname");
            field.setValue(bewerberDTO.getPersonalien().getVorname());
            field = pDAcroForm.getField("Name");
            field.setValue(bewerberDTO.getPersonalien().getName());
            field = pDAcroForm.getField("Geburtsdatum");
            field.setValue(bewerberDTO.getPersonalien().getGeburtsdatum().toString());
            field = pDAcroForm.getField("Staatsangehörigkeit");
            field.setValue(bewerberDTO.getPersonalien().getNationalitaet());
            field = pDAcroForm.getField("Anschrift (Straße)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getStraße());
            field = pDAcroForm.getField("Anschrift (Hausnummer)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getHausnummer());
            field = pDAcroForm.getField("Anschrift (PLZ)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getPLZ());
            field = pDAcroForm.getField("Anschrift (ORT)");
            field.setValue(bewerberDTO.getPersonalien().getAdresse().getWohnort());


            /*field = pDAcroForm.getField("Neueinstellung");
            field.setValue(bewerberDTO.getPraeferenzen().getEinstiegTyp().toString());*/  //das feld neueinstellung hat einen anderen namen, leider weiß ich nicht welchen
            /*field = pDAcroForm.getField("Weiterbeschäftigung"); / //anderer Name, muss nachschauen wie das Feld richtig heißt
            field.setValue(bewerberDTO.getPraeferenzen().getEinstiegTyp().toString()); */


            field = pDAcroForm.getField("Stunden");
            field.setValue(bewerberDTO.getPraeferenzen().getMinWunschStunden() + " - " + bewerberDTO.getPraeferenzen().getMaxWunschStunden());
            field = pDAcroForm.getField("Immatrikulation");
            field.setValue(pruefeStatus(bewerberDTO));
            field = pDAcroForm.getField("Studiengang");
            field.setValue(bewerberDTO.getKarriere().getImmartikulationsStatus().getFachrichtung());

            field = pDAcroForm.getField("Bemerkung zum Antrag");
            field.setValue("On");
            field = pDAcroForm.getField("Bemerkung zum Antrag1");
            field.setValue(bewerberDTO.getKarriere().getArbeitserfahrung() + "/n" + bewerberDTO.getPraeferenzen().getKommentar() + "/n" + bewerberDTO.getPraeferenzen().getEinschraenkungen());

            field = pDAcroForm.getField("Schilderung der auszuübenden Tätigkeit");
            field.setValue(bewerberDTO.getPraeferenzen().getBerufModul().getBeruf().toString());






            //Speichern der Datei
            pDDocument.save("/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/output2t.pdf");
            pDDocument.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }




    public String pruefeStatus(BewerberDTO bewerberDTO){
        if(bewerberDTO.getKarriere().getImmartikulationsStatus().isStatus() == true){
            return "On";
        }
        return "Off";


    }
}
