package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import mops.domain.services.IModelSerice;
import org.springframework.security.core.parameters.P;

@SpringBootTest
public class ModelServiceTest {
    
    private transient IModelSerice mappingService;

    @BeforeEach
     void setUp() {
        mappingService = new ModelService();
        
    }



    @Test
    public void personalienDTOZuPersonalienModel() {
        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setHausnummer("11a");
        adresseDTO.setPLZ("40233");
        adresseDTO.setStrasse("Simrockstr");
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
        assertEquals(adresseDTO.getStrasse(), adresse.getStrasse());
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

    @Test
    public void karriereDTOIsNullTest(){
        KarriereDTO karriereDTO = null;
        Karriere karriere = mappingService.load(karriereDTO);
        assertNull(karriere);
    }

    @Test
    public void karriereDTOzuKarriereModel(){
        ImmartikulationsStatusDTO immartikulationsStatusDTO = new ImmartikulationsStatusDTO(true, "Informatik");
        StudiengangAbschlussDTO studiengangAbschlussDTO = new StudiengangAbschlussDTO("Informatik", "Bachelor");
        KarriereDTO karriereDTO = new KarriereDTO("bei Apple Store gearbeitet.", immartikulationsStatusDTO, studiengangAbschlussDTO);

        Karriere karriere = mappingService.load(karriereDTO);
        StudiengangAbschluss studiengangAbschluss = karriere.getFachAbschluss();
        ImmartikulationsStatus immartikulationsStatus = karriere.getImmartikulationsStatus();
        assertNotNull(karriere);


        assertEquals(karriereDTO.getArbeitserfahrung(), karriere.getArbeitserfahrung());
        assertEquals(immartikulationsStatusDTO.getFachrichtung(), immartikulationsStatus.getFachrichtung());
        assertEquals(immartikulationsStatusDTO.isStatus(), immartikulationsStatus.isStatus());
        assertEquals(studiengangAbschlussDTO.getStudiengang(), studiengangAbschluss.getStudiengang());
        assertEquals(studiengangAbschlussDTO.getAbschluss(), studiengangAbschluss.getAbschluss());
    }

    @Test
    public void modulAuswahlIsNull() {
        ModulAuswahlDTO modulAuswahlDTO = null;
        ModulAuswahl modulAuswahl = mappingService.load(modulAuswahlDTO);
        assertNull(modulAuswahl);
    }

    @Test
    public void modulAuswahlDTOzuModulAuswahl(){
        ModulDTO modulDTO = new ModulDTO("propra2", "jens@hhu.de", "Jens");
        ModulAuswahlDTO modulAuswahlDTO = new ModulAuswahlDTO(modulDTO, 1);

        ModulAuswahl modulAuswahl = mappingService.load(modulAuswahlDTO);
        Modul modul = modulAuswahl.getModul();

        assertNotNull(modul);
        assertNotNull(modulAuswahl);

        assertEquals(modulDTO.getDozentMail(), modul.getDozent().getDozentMail());
        assertEquals(modulDTO.getDozentName(), modul.getDozent().getDozentName());
        assertEquals(modulAuswahlDTO.getPrioritaet(), modulAuswahl.getPrioritaet());
    }


    @Test
    public void praeferenzenDTOzuPraeferenzen(){
        ModulDTO modulDTO1 = new ModulDTO("propra2", "jens@hhu.de", "Jens Bendisposto");
        ModulDTO modulDTO2 = new ModulDTO("Aldat", "stephan@hhu.de", "Stephan Mueller");
        ModulDTO modulDTO3 = new ModulDTO("RDB", "shoetner@hhu.de", "Michael Schoetner");

        ModulAuswahlDTO modulAuswahlDTO1 = new ModulAuswahlDTO(modulDTO1, 2);
        ModulAuswahlDTO modulAuswahlDTO2 = new ModulAuswahlDTO(modulDTO2, 1);
        ModulAuswahlDTO modulAuswahlDTO3 = new ModulAuswahlDTO(modulDTO3, 3);

        Modul modul1 = mappingService.loadModul(modulDTO1);

        List<ModulAuswahlDTO> modulAuswahlDTOSlist = new LinkedList<ModulAuswahlDTO>();
        modulAuswahlDTOSlist.add(modulAuswahlDTO1);
        modulAuswahlDTOSlist.add(modulAuswahlDTO2);
        modulAuswahlDTOSlist.add(modulAuswahlDTO3);

        BerufModulDTO berufModul = new BerufModulDTO(Beruf.Tutor, modulDTO1);

        PraeferenzenDTO praeferenzenDTO = new PraeferenzenDTO(6, 8, modulAuswahlDTOSlist, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul, TutorenSchulungTeilnahme.TEILNAHME);

        Praeferenzen praeferenzen = mappingService.load(praeferenzenDTO);
        assertNotNull(praeferenzen);

        assertEquals(praeferenzenDTO.getMinWunschStunden(), praeferenzen.getMinWunschStunden());
        assertEquals(praeferenzenDTO.getMaxWunschStunden(), praeferenzen.getMaxWunschStunden());
        assertEquals(praeferenzenDTO.getKommentar(), praeferenzen.getKommentar());
        assertEquals(praeferenzenDTO.getEinstiegTyp(), praeferenzen.getEinstiegTyp());
        assertEquals(praeferenzenDTO.getEinschraenkungen(), praeferenzen.getEinschraenkungen());

        assertEquals(praeferenzenDTO.getBerufModul().getBeruf(), praeferenzen.getBerufModul().getBeruf());
        assertEquals(praeferenzenDTO.getBerufModul().getModul().getDozentName(), praeferenzen.getBerufModul().getModul().getDozent().getDozentName());
        assertEquals(praeferenzenDTO.getBerufModul().getModul().getDozentMail(), praeferenzen.getBerufModul().getModul().getDozent().getDozentMail());
        assertEquals(praeferenzenDTO.getBerufModul().getModul().getModulName(), praeferenzen.getBerufModul().getModul().getModulName());
        assertEquals(praeferenzenDTO.getTutorenSchulungTeilnahme(), praeferenzen.getTutorenSchulungTeilnahme());

        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getPrioritaet(), praeferenzen.getModulAuswahl().get(1).getPrioritaet());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getModulName(), praeferenzen.getModulAuswahl().get(1).getModul().getModulName());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentName(), praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentName());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentMail(), praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentMail());
    }

    @Test
    public void abschlussDTOzuAbschlussTest(){
        StudiengangAbschlussDTO studiengangAbschlussDTO = new StudiengangAbschlussDTO("Informatik", "Master");

        StudiengangAbschluss studiengangAbschluss = mappingService.load(studiengangAbschlussDTO);

        assertEquals(studiengangAbschlussDTO.getAbschluss(), studiengangAbschluss.getAbschluss());
        assertEquals(studiengangAbschlussDTO.getStudiengang(), studiengangAbschluss.getStudiengang());
    }

    @Test
    public void loadModulListTest(){
        ModulDTO modulDTO1 = new ModulDTO("propra2", "jens@hhu.de", "Jens Bendisposto");
        ModulDTO modulDTO2 = new ModulDTO("Aldat", "stephan@hhu.de", "Stephan Mueller");

        List<ModulDTO> modulDTOList = new LinkedList<ModulDTO>();

        modulDTOList.add(modulDTO1);
        modulDTOList.add(modulDTO2);

        List<Modul> modulList = mappingService.loadModulList(modulDTOList);

        assertEquals(modulDTOList.get(0).getModulName(), modulList.get(0).getModulName());
        assertEquals(modulDTOList.get(0).getDozentMail(), modulList.get(0).getDozent().getDozentMail());
        assertEquals(modulDTOList.get(0).getDozentName(), modulList.get(0).getDozent().getDozentName());

        assertEquals(modulDTOList.get(1).getModulName(), modulList.get(1).getModulName());
        assertEquals(modulDTOList.get(1).getDozentMail(), modulList.get(1).getDozent().getDozentMail());
        assertEquals(modulDTOList.get(1).getDozentName(), modulList.get(1).getDozent().getDozentName());
    }

}