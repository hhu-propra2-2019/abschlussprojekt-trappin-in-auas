package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.services.IMappingService;
import mops.services.MappingService;

@SpringBootTest
public class MappingServiceTest {
    
    private transient IMappingService mappingService;

    @BeforeEach
     void setUp() {
        mappingService = new MappingService();
        
    }

    @Test
    public void bestandeneModuleDTOZuBestandeneModuleModel(){
        //TODO: create a generator for this
        //TODO: differentiate DTO embeddables from models
        ModulDTO modulDTO = new ModulDTO("RA", "golov@hhu.de", "janine golov");

        BestandeneModuleDTO bestandeneModuleDTO = new BestandeneModuleDTO();
        bestandeneModuleDTO.setNote(1.3);
        bestandeneModuleDTO.setModul(modulDTO);
        System.out.println(mappingService);
        BestandeneModule bestandeneModule = mappingService.load(bestandeneModuleDTO);
        Modul modul = bestandeneModule.getModul();

        assertNotNull(bestandeneModule);
        assertNotNull(modul);

        //are those checks below actually nessecary? not sure
        assertEquals(bestandeneModule.getNote(), 1.3);
        assertEquals(modulDTO.getModul(), modul.getModulName());
    }

    /**
     * Make sure the mappingservice
     * does not break when passing null
     */
    @Test
    public void bestandenesModulDTOIsNullMappingReturnsNull(){
        BestandeneModuleDTO bestandeneModuleDTO = null;
        BestandeneModule bestandeneModule = mappingService.load(bestandeneModuleDTO);

        assertNull(bestandeneModule);
    }

    @Test
    public void personalienDTOZuPersonalienModel(){
        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setHausnummer("11a");
        adresseDTO.setPLZ("40233");
        adresseDTO.setStraße("Simrockstr");
        adresseDTO.setWohnort("Düsseldorf");

        PersonalienDTO personalienDTO = new PersonalienDTO();
        personalienDTO.setAdresse(adresseDTO);
        personalienDTO.setAlter(20);
        try{
            personalienDTO.setGeburtsdatum(Date.from(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("15.07.1999").toInstant()));
        }catch(Exception e){
            personalienDTO.setGeburtsdatum(null);
        }
        personalienDTO.setGeburtsort("Swetlana");
        personalienDTO.setName("Wick");
        personalienDTO.setNationalitaet("Terminator");
        personalienDTO.setVorname("John");
        personalienDTO.setUnikennung("johwi200");
        
        Personalien personalien = mappingService.load(personalienDTO);
        
        Adresse adresse = personalien.getAdresse();

        assertNotNull(personalien);
        assertNotNull(adresse);

        assertEquals(adresseDTO.getHausnummer(), adresse.getHausnummer());
        assertEquals(adresseDTO.getPLZ(), adresse.getPLZ());
        assertEquals(adresseDTO.getStraße(), adresse.getStraße());
        assertEquals(adresseDTO.getWohnort(), adresse.getWohnort());

        assertEquals(personalienDTO.getAlter(), personalien.getAlter());
        assertEquals(personalienDTO.getGeburtsdatum(), personalien.getGeburtsdatum());
        assertEquals(personalienDTO.getGeburtsort(), personalien.getGeburtsort());
        assertEquals(personalienDTO.getName(), personalien.getName());
        assertEquals(personalienDTO.getNationalitaet(), personalien.getNationalitaet());
        assertEquals(personalienDTO.getUnikennung(), personalien.getUnikennung());
        assertEquals(personalienDTO.getVorname(), personalien.getVorname());
    }

    @Test
    public void personalienDTOIsNullMappingReturnsNull(){
        PersonalienDTO personalienDTO = null;
        Personalien personalien = mappingService.load(personalienDTO);

        assertNull(personalien);
    }
}