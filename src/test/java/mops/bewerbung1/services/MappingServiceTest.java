package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.Date;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.services.IMappingService;

@SpringBootTest
public class MappingServiceTest {
    private IMappingService mappingService;

/*
    @Test
    public void bestandeneModuleDTOZuBestandeneModuleModel(){
        //TODO: create a generator for this
        //TODO: differentiate DTO embeddables from models
        ModulDTO modulDTO = new ModulDTO();
        modulDTO.setModul("RA");
        
        StudiengangAbschlussDTO abschlussDTO = new StudiengangAbschlussDTO();
        abschlussDTO.setAbschluss("Bachelor");
        abschlussDTO.setStudiengang("Informatik");

        ImmartikulationsStatusDTO statusDTO = new ImmartikulationsStatusDTO();
        statusDTO.setFachrichtung("Informatik");
        statusDTO.setStatus(true);

        KarriereDTO karriereDTO = new KarriereDTO();
        karriereDTO.setArbeitserfahrung("vorhanden");
        karriereDTO.setFachAbschluss(abschlussDTO);
        karriereDTO.setImmartikulationsStatus(statusDTO);

        BestandeneModuleDTO bestandeneModuleDTO = new BestandeneModuleDTO();
        bestandeneModuleDTO.setNote(1.3);
        bestandeneModuleDTO.setKarriere(karriereDTO);
        bestandeneModuleDTO.setModul(modulDTO);

        BestandeneModule bestandeneModule = mappingService.load(bestandeneModuleDTO);
        Karriere karriere = bestandeneModule.getKarriere();
        Modul modul = bestandeneModule.getModul();
        ImmartikulationsStatus status = karriere.getImmartikulationsStatus();
        StudiengangAbschluss abschluss = karriere.getFachAbschluss();

        assertNotNull(bestandeneModule);
        assertNotNull(karriere);
        assertNotNull(modul);
        assertNotNull(status);

        //are those checks below actually nessecary? not sure
        assertEquals(bestandeneModule.getNote(), 1.3);

        assertEquals(karriereDTO.getArbeitserfahrung(), karriere.getArbeitserfahrung());

        assertEquals(abschlussDTO.getAbschluss(), abschluss.getAbschluss());
        assertEquals(abschlussDTO.getStudiengang(), abschluss.getStudiengang());

        assertEquals(statusDTO.getFachrichtung(), status.getFachrichtung());

        assertEquals(modulDTO.getModul(), modul.getModul());
    }
*/
    /**
     * Make sure the mappingservice
     * does not break when passing null
     *//*
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
        personalienDTO.setGeburtsdatum(Date.from(Instant.parse("22.02.1999")));
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
    }*/
}