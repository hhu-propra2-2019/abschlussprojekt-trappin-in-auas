package mops.bewerbung1.services;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
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
    public void pruefeKeinFachAbschluss() throws ParseException {


        //Arrange

        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");


        Karriere karriere = new Karriere("war Praktikant",immartikulationsStatus ,null,null);


        //Act






    }




    @Test
    public void pruefeFachAbschluss() throws ParseException {


        //Arrange

        Date geburtsdatum = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String datum = "2.10.1995";
        geburtsdatum = ft.parse(datum);
        Adresse a = new Adresse("40789","Monheim","Tegeler Straße","15");
        Personalien personalien = new Personalien(a,"1234","Akin","Kilincarslan",geburtsdatum,24,"Leverkusen","Deutsch");
        ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
        Karriere karriere = new Karriere("war Praktikant",immartikulationsStatus ,null,null);


        //Act






    }




}
