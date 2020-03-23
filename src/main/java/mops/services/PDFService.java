package mops.services;


import mops.domain.database.dto.BewerberDTO;
import mops.domain.models.*;

import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;


public class PDFService {

      /* for (PDField field2 : pDAcroForm.getFields()){
                System.out.println(field2.getFullyQualifiedName());
            }*/ //spuckt alle titelfelder aus



    public String filedirectory(BewerberDTO bewerberDTO) throws Exception {
        if(bewerberDTO.getKarriere().getFachAbschluss() == null) {
            String path = "../../../resources/static/321_Antrag_Beschaeftigung_stud_Hilfskraefte.pdf";
          return path;
        }
        else {
        String path = "../../../resources/static/323_Antrag_Beschaeftigung_wiss_Hilfskraefte_mit_BA.pdf";
        return path;

        }




    }




    public void fillStudentHilfskraft(BewerberDTO bewerberDTO,String filedirectory) throws Exception {


        try {
            PDDocument pDDocument = PDDocument.load(new File(filedirectory));


            if (pDDocument.isEncrypted()) {
                try {
                    pDDocument.setAllSecurityToBeRemoved(true);
                } catch (Exception e) {
                    throw new Exception("The document is encrypted, and we can't decrypt it.", e);
                }
            }


            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();


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

            field = pDAcroForm.getField("Vertragsart");
            field.setValue(pruefeVertragsart(bewerberDTO));

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
            pDDocument.save("../../../resources/static/output.pdf");
            pDDocument.close();

        } catch (Exception e) {

            e.printStackTrace();

        }



    }



    public void fillWissenHilfskraft(BewerberDTO bewerberDTO, String filedirectory) throws Exception {

        try {
            PDDocument pDDocument = PDDocument.load(new File(filedirectory));


            if (pDDocument.isEncrypted()) {
                try {
                    pDDocument.setAllSecurityToBeRemoved(true);
                } catch (Exception e) {
                    throw new Exception("The document is encrypted, and we can't decrypt it.", e);
                }
            }
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();



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


            field = pDAcroForm.getField("Vertragsart");
            field.setValue(pruefeVertragsart(bewerberDTO));


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
            pDDocument.save("../../../resources/static//output2.pdf");
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


    public String pruefeVertragsart(BewerberDTO bewerberDTO) {
        if (bewerberDTO.getPraeferenzen().getEinstiegTyp() == EinstiegTyp.WEITERBESCHAEFTIGUNG){

            return "Weiterbeschäftigung";


        }
    else if(bewerberDTO.getPraeferenzen().getEinstiegTyp() == EinstiegTyp.NEUEINSTIEG){

        return "Einstellung";

        }

    else{

        return "Off";
        }

    }

}
