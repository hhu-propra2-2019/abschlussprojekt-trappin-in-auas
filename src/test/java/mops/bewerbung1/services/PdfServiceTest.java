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
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;

//ToDo aber erst wenn pdf fertig ist. Unser Ziel ist es die check methode zu überpruefen.
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class PdfServiceTest {
    private static final String PDF_PATH = "./src/main/resources/static/";

    @Mock
    private transient BewerberRepository bewerberRepository;

    private transient Bewerber b;
    private transient Karriere karriere;
    private transient Praeferenzen praeferenzen;
    private transient PDFService pdfService;

    @BeforeEach
    public void setUp(){
        b = new Bewerber();
        karriere = new Karriere();
        praeferenzen = new Praeferenzen();
        pdfService = new PDFService();
    }

    @Test
    public void checkFileDirecoryWissHilfsKraft(){
        karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
        b.setKarriere(karriere);
        String pdffile = pdfService.fileDirectory(b);
        Assert.assertEquals(PDF_PATH + "wissenschaftliche_Hilfskraft.pdf",pdffile);
    }

    @Test
    public void checkFileDirecoryStudHilfsKraft(){
        karriere.setFachAbschluss(null);
        b.setKarriere(karriere);
        String pdffile = pdfService.fileDirectory(b);
        Assert.assertEquals(PDF_PATH + "studentische_Hilfskraft.pdf",pdffile);
    }

    @Test
    public void pruefeKeinAbschluss(){
        karriere.setFachAbschluss(null);
        b.setKarriere(karriere);
        boolean status = pdfService.hatAbschluss(b);
        Assert.assertEquals(false,status);
    }

    @Test
    public void pruefeAbschluss(){
        karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
        b.setKarriere(karriere);
        boolean status = pdfService.hatAbschluss(b);
        Assert.assertEquals(true,status);
    }

    @Test
    public void pruefeImmaStatus(){
        karriere.setImmatrikulationsStatus(new ImmatrikulationsStatus(true, "Informatik"));
        b.setKarriere(karriere);
        Assert.assertEquals("On",pdfService.pruefeStatus(b));
    }

    @Test
    public void pruefeKeinImmaStatus(){
        karriere.setImmatrikulationsStatus(new ImmatrikulationsStatus(false, ""));
        b.setKarriere(karriere);
        String status = pdfService.pruefeStatus(b);
        Assert.assertEquals("Off",status);
    }

    @Test
    public void pruefeVertragsart1(){
        praeferenzen.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
        b.setPraeferenzen(praeferenzen);
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Einstellung",vertragsart);
    }

    @Test
    public void pruefeVertragsart2(){
        praeferenzen.setEinstiegTyp(EinstiegTyp.WEITERBESCHAEFTIGUNG);
        b.setPraeferenzen(praeferenzen);
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Weiterbeschäftigung",vertragsart);
    }

    @Test
    public void pruefeVertragsart3(){
        praeferenzen.setEinstiegTyp(EinstiegTyp.WIEDEREINSTIEG);
        b.setPraeferenzen(praeferenzen);
        String vertragsart = pdfService.pruefeVertragsart(b);
        Assert.assertEquals("Off",vertragsart);
    }

}
