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
import mops.domain.services.IDTOService;
import mops.domain.services.IModelService;
import mops.services.DTOService;
import mops.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class ModelServiceTest {

  private transient IDTOService dtoService;
  private transient IModelService modelService;

  @BeforeEach
  void setUp() {
    dtoService = new DTOService();
    modelService = new ModelService();
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
    try {
      personalienDTO.setGeburtsdatum(
          Date.from(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("15.07.1999").toInstant()));
    } catch (Exception e) {
      personalienDTO.setGeburtsdatum(null);
    }
    personalienDTO.setGeburtsort("Swetlana");
    personalienDTO.setName("Wick");
    personalienDTO.setNationalitaet("Terminator");
    personalienDTO.setVorname("John");

    Personalien personalien = modelService.load(personalienDTO);

    Adresse adresse = personalien.getAdresse();

    assertNotNull(personalien);
    assertNotNull(adresse);

    assertEquals(adresseDTO.getHausnummer(), adresse.getHausnummer());
    assertEquals(adresseDTO.getPLZ(), adresse.getPLZ());
    assertEquals(adresseDTO.getStrasse(), adresse.getStrasse());
    assertEquals(adresseDTO.getWohnort(), adresse.getWohnOrt());

    assertEquals(personalienDTO.getAlter(), personalien.getAlter());
    assertEquals(personalienDTO.getGeburtsdatum(), personalien.getGeburtsdatum());
    assertEquals(personalienDTO.getGeburtsort(), personalien.getGeburtsort());
    assertEquals(personalienDTO.getName(), personalien.getName());
    assertEquals(personalienDTO.getNationalitaet(), personalien.getNationalitaet());
    assertEquals(personalienDTO.getVorname(), personalien.getVorname());
  }

  @Test
  public void personalienDTOIsNullMappingReturnsNull() {
    PersonalienDTO personalienDTO = null;
    Personalien personalien = modelService.load(personalienDTO);

    assertNull(personalien);
  }

    @Test
    public void karriereDTOIsNullTest(){
        KarriereDTO karriereDTO = null;
        Karriere karriere = modelService.load(karriereDTO);
        assertNull(karriere);
    }

    @Test
    public void karriereDTOzuKarriereModel(){
        ImmartikulationsStatusDTO immartikulationsStatusDTO = new ImmartikulationsStatusDTO(true, "Informatik");
        StudiengangAbschlussDTO studiengangAbschlussDTO = new StudiengangAbschlussDTO("Mathematik", "Bachelor","HHU");
        KarriereDTO karriereDTO = new KarriereDTO("bei Apple Store gearbeitet.", immartikulationsStatusDTO, studiengangAbschlussDTO);

        Karriere karriere = modelService.load(karriereDTO);
        StudiengangAbschluss studiengangAbschluss = karriere.getFachAbschluss();
        ImmatrikulationsStatus immatrikulationsStatus = karriere.getImmartikulationsStatus();
        assertNotNull(karriere);


        assertEquals(karriereDTO.getArbeitserfahrung(), karriere.getArbeitserfahrung());
        assertEquals(immartikulationsStatusDTO.getFachrichtung(), immatrikulationsStatus.getFachrichtung());
        assertEquals(immartikulationsStatusDTO.isStatus(), immatrikulationsStatus.isStatus());
        assertEquals(studiengangAbschlussDTO.getStudiengang(), studiengangAbschluss.getStudiengang());
        assertEquals(studiengangAbschlussDTO.getUni(), studiengangAbschluss.getUni());
        assertEquals(studiengangAbschlussDTO.getAbschluss(), studiengangAbschluss.getAbschluss());
    }

    @Test
    public void modulAuswahlIsNull() {
        ModulAuswahlDTO modulAuswahlDTO = null;
        ModulAuswahl modulAuswahl = modelService.load(modulAuswahlDTO);
        assertNull(modulAuswahl);
    }

    @Test
    public void modulAuswahlDTOzuModulAuswahl(){
        ModulDTO modulDTO = new ModulDTO("propra2", "jens@hhu.de", "Jens");
        ModulAuswahlDTO modulAuswahlDTO = new ModulAuswahlDTO(modulDTO, 1, 2.0, Beruf.Korrektor);

        ModulAuswahl modulAuswahl = modelService.load(modulAuswahlDTO);
        Modul modul = modulAuswahl.getModul();

        assertNotNull(modul);
        assertNotNull(modulAuswahl);

        assertEquals(modulDTO.getDozentMail(), modul.getDozent().getDozentMail());
        assertEquals(modulDTO.getDozentName(), modul.getDozent().getDozentName());
        assertEquals(modulAuswahlDTO.getPrioritaet(), modulAuswahl.getPrioritaet());
        assertEquals(modulAuswahlDTO.getNote(), modulAuswahl.getNote());
        assertEquals(modulAuswahlDTO.getBeruf(), modulAuswahl.getBeruf());
    }


    @Test
    public void praeferenzenDTOzuPraeferenzen(){
        ModulDTO modulDTO1 = new ModulDTO("propra2" , "Jens Bendisposto","jens@hhu.de");
        ModulDTO modulDTO2 = new ModulDTO("Aldat", "Stephan Mueller", "stephan@hhu.de");
        ModulDTO modulDTO3 = new ModulDTO("RDB", "Michael Schoetner" ,"shoetner@hhu.de");

        ModulAuswahlDTO modulAuswahlDTO1 = new ModulAuswahlDTO(modulDTO1, 2, 2.0, Beruf.Korrektor);
        ModulAuswahlDTO modulAuswahlDTO2 = new ModulAuswahlDTO(modulDTO2, 1, 3.3, Beruf.Korrektor);
        ModulAuswahlDTO modulAuswahlDTO3 = new ModulAuswahlDTO(modulDTO3, 3, 1.0, Beruf.Korrektor);

        

        List<ModulAuswahlDTO> modulAuswahlDTOSlist = new LinkedList<ModulAuswahlDTO>();
        modulAuswahlDTOSlist.add(modulAuswahlDTO1);
        modulAuswahlDTOSlist.add(modulAuswahlDTO2);
        modulAuswahlDTOSlist.add(modulAuswahlDTO3);

        PraeferenzenDTO praeferenzenDTO = new PraeferenzenDTO(6, 8, modulAuswahlDTOSlist, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);

        Praeferenzen praeferenzen = modelService.load(praeferenzenDTO);
        assertNotNull(praeferenzen);

        assertEquals(praeferenzenDTO.getMinWunschStunden(), praeferenzen.getMinWunschStunden());
        assertEquals(praeferenzenDTO.getMaxWunschStunden(), praeferenzen.getMaxWunschStunden());
        assertEquals(praeferenzenDTO.getKommentar(), praeferenzen.getKommentar());
        assertEquals(praeferenzenDTO.getEinstiegTyp(), praeferenzen.getEinstiegTyp());
        assertEquals(praeferenzenDTO.getEinschraenkungen(), praeferenzen.getEinschraenkungen());

        assertEquals(praeferenzenDTO.getModulAuswahl().get(0).getBeruf(), praeferenzen.getModulAuswahl().get(0).getBeruf());
        assertEquals(praeferenzenDTO.getTutorenSchulungTeilnahme(), praeferenzen.getTutorenSchulungTeilnahme());

        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getPrioritaet(), praeferenzen.getModulAuswahl().get(1).getPrioritaet());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getNote(), praeferenzen.getModulAuswahl().get(1).getNote());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getModulName(), praeferenzen.getModulAuswahl().get(1).getModul().getModulName());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentName(), praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentName());
        assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentMail(), praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentMail());
    }

    @Test
    public void abschlussDTOzuAbschlussTest(){
        StudiengangAbschlussDTO studiengangAbschlussDTO = new StudiengangAbschlussDTO("Chemie", "Master","HHU");

        StudiengangAbschluss studiengangAbschluss = modelService.load(studiengangAbschlussDTO);

        assertEquals(studiengangAbschlussDTO.getAbschluss(), studiengangAbschluss.getAbschluss());
        assertEquals(studiengangAbschlussDTO.getStudiengang(), studiengangAbschluss.getStudiengang());
    }

    @Test
    public void loadModulListTest(){
        ModulDTO modulDTO1 = new ModulDTO("propra2",  "Jens Bendisposto","jens@hhu.de");
        ModulDTO modulDTO2 = new ModulDTO("Aldat", "Stephan Mueller" ,"stephan@hhu.de");

        List<ModulDTO> modulDTOList = new LinkedList<ModulDTO>();

        modulDTOList.add(modulDTO1);
        modulDTOList.add(modulDTO2);

        List<Modul> modulList = modelService.loadModulList(modulDTOList);

        assertEquals(modulDTOList.get(0).getModulName(), modulList.get(0).getModulName());
        assertEquals(modulDTOList.get(0).getDozentMail(), modulList.get(0).getDozent().getDozentMail());
        assertEquals(modulDTOList.get(0).getDozentName(), modulList.get(0).getDozent().getDozentName());

        assertEquals(modulDTOList.get(1).getModulName(), modulList.get(1).getModulName());
        assertEquals(modulDTOList.get(1).getDozentMail(), modulList.get(1).getDozent().getDozentMail());
        assertEquals(modulDTOList.get(1).getDozentName(), modulList.get(1).getDozent().getDozentName());
    }

}