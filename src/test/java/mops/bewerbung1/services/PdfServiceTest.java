package mops.bewerbung1.services;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import mops.services.PDFService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static jdk.vm.ci.code.CodeUtil.M;
import static org.mockito.Mockito.mock;

//ToDo aber erst wenn pdf fertig ist. Unser Ziel ist es die check methode zu überpruefen.
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class PdfServiceTest {
    private static final String PDF_PATH = "./src/main/resources/static/";

    @Test
    public void checkFileDirecoryWissHilfsKraft(){
        //arrange
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setArbeitserfahrung("Viel");
        karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
        b.setKarriere(karriere);
        //Act
        PDFService pdfService = new PDFService();
        String pdffile = pdfService.fileDirectory(b);
        //Assert
        Assert.assertEquals(PDF_PATH + "wissenschaftliche_Hilfskraft.pdf",pdffile);
    }

    @Test
    public void checkFileDirecoryStudHilfsKraft(){
        //arrange
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setArbeitserfahrung("Viel");
        karriere.setFachAbschluss(null);
        b.setKarriere(karriere);
        //Act
        PDFService pdfService = new PDFService();
        String pdffile = pdfService.fileDirectory(b);
        //Assert
        Assert.assertEquals(PDF_PATH + "studentische_Hilfskraft.pdf",pdffile);
    }

    @Test
    public void pruefeKeinAbschluss(){
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setArbeitserfahrung("Viel");
        karriere.setFachAbschluss(null);
        b.setKarriere(karriere);
        PDFService pdfService = new PDFService();
        boolean status = pdfService.hatAbschluss(b);
        Assert.assertEquals(false,status);
    }

    @Test
    public void pruefeAbschluss(){
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setArbeitserfahrung("Viel");
        karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
        b.setKarriere(karriere);
        PDFService pdfService = new PDFService();
        boolean status = pdfService.hatAbschluss(b);
        Assert.assertEquals(true,status);
    }

    @Test
    public void pruefeImmaStatus(){
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setImmartikulationsStatus(new ImmartikulationsStatus(true, "Informatik"));
        b.setKarriere(karriere);
        PDFService pdfService = new PDFService();
        Assert.assertEquals("On",pdfService.pruefeStatus(b));
    }

    @Test
    public void pruefeKeinImmaStatus(){
        Bewerber b = new Bewerber();
        Karriere karriere = new Karriere();
        karriere.setImmartikulationsStatus(new ImmartikulationsStatus(false, ""));
        b.setKarriere(karriere);
        PDFService pdfService = new PDFService();
        String status = pdfService.pruefeStatus(b);
        Assert.assertEquals("Off",status);
    }

    @Test
    public void pruefeVertragsart1(){
        Bewerber b = new Bewerber();
        Praeferenzen praeferenzen = new Praeferenzen();
        praeferenzen.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
        b.setPraeferenzen(praeferenzen);
        PDFService pdfService = new PDFService();
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Einstellung",vertragsart);
    }

    @Test
    public void pruefeVertragsart2(){
        Bewerber b = new Bewerber();
        Praeferenzen praeferenzen = new Praeferenzen();
        praeferenzen.setEinstiegTyp(EinstiegTyp.WEITERBESCHAEFTIGUNG);
        b.setPraeferenzen(praeferenzen);
        PDFService pdfService = new PDFService();
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Weiterbeschäftigung",vertragsart);
    }

    @Test
    public void pruefeVertragsart3(){
        Bewerber b = new Bewerber();
        Praeferenzen praeferenzen = new Praeferenzen();
        praeferenzen.setEinstiegTyp(EinstiegTyp.WIEDEREINSTIEG);
        b.setPraeferenzen(praeferenzen);
        PDFService pdfService = new PDFService();
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Off",vertragsart);
    }

}
