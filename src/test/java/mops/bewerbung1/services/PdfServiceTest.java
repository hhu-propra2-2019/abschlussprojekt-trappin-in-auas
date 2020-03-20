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
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;

//ToDo aber erst wenn pdf fertig ist. Unser Ziel ist es die check methode zu überpruefen.
@SpringBootTest
public class PdfServiceTest {

    private BewerberDTO bewerberDTO;

    @BeforeEach
    void setUp() {
        this.bewerberDTO = mock(BewerberDTO.class);

    }
        /* for (PDField field2 : pDAcroForm.getFields()){
                System.out.println(field2.getFullyQualifiedName());
            }*/ //spuckt alle titelfelder aus

    @Test
    public void pruefeKeinFachAbschluss() throws Exception {


        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        Karriere karriere = new Karriere("war Praktikant",immartikulationsStatus ,null,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.NEUEINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //To DO
        PDFService pdfService = new PDFService();


        //Act
        String pruefeFachabschluss = pdfService.filedirectory(bewerberDTO);
        String AntragOhneFachAbschluss = "/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/321_Antrag_Beschaeftigung_stud_Hilfskraefte.pdf";


        //Assert
        Assert.assertEquals(AntragOhneFachAbschluss, pruefeFachabschluss);



    }




    @Test
    public void pruefeFachAbschluss() throws Exception {


        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",immartikulationsStatus,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.NEUEINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();


        //Act
        String pruefeFachabschluss = pdfService.filedirectory(bewerberDTO);
        String AntragOhneFachAbschluss = "/home/heyoka/Schreibtisch/progra2/projekt/abschlussprojekt-trappin-in-auas/323_Antrag_Beschaeftigung_wiss_Hilfskraefte_mit_BA.pdf";


        //Assert
       Assert.assertEquals(AntragOhneFachAbschluss, pruefeFachabschluss);







    }

    @Test
    public void testStatus() throws Exception {

        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",immartikulationsStatus,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.NEUEINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();


        //Act
        String s = pdfService.pruefeStatus(bewerberDTO);


        //Assert
        Assert.assertEquals("On", s);


    }

    @Test
    public void testNichtStatus() throws Exception {

        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",null,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.NEUEINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();


        //Act
        String s = pdfService.pruefeStatus(bewerberDTO);


        //Assert
        Assert.assertEquals("Off", s);


    }


    @Test
    public void testVertragsart() throws Exception {
        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",null,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.NEUEINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();

        //Act
        String s = pdfService.pruefeVertragsart(bewerberDTO);


        //Assert
        Assert.assertEquals("Einstellung", s);



    }


    @Test
    public void testVertragsart2() throws Exception {
        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",null,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.WEITERBESCHAEFTIGUNG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();

        //Act
        String s = pdfService.pruefeVertragsart(bewerberDTO);


        //Assert
        Assert.assertEquals("Weiterbeschäftigung", s);



    }

    @Test
    public void testVertragsart3() throws Exception {
        //Arrange
        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
        Karriere karriere = new Karriere("war Praktikant",null,studiengangAbschluss,null);
        Praeferenzen praeferenzen = new Praeferenzen(15,20,null,null,EinstiegTyp.WIEDEREINSTIEG,null,null,TutorenSchulungTeilnahme.NICHTTEILNAHME);
        BewerberDTO bewerberDTO = new BewerberDTO(karriere,personalien,praeferenzen); //TO DO
        PDFService pdfService = new PDFService();

        //Act
        String s = pdfService.pruefeVertragsart(bewerberDTO);


        //Assert
        Assert.assertEquals("Off", s);



    }

}
