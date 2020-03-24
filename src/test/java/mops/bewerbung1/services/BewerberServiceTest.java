package mops.bewerbung1.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.models.Beruf;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;
import mops.services.BewerberService;
import mops.services.DTOService;
import mops.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class BewerberServiceTest {

  private transient IBewerberService bewerberService;
  private transient BewerberRepository bewerberRepository;
  private transient DTOService dtoService;
  private transient ModelService modelService;

  @BeforeEach
  void setUp() {
    this.bewerberRepository = mock(BewerberRepository.class);
    this.dtoService = new DTOService();
    this.modelService = new ModelService();
   this.bewerberService = new BewerberService(bewerberRepository, dtoService, modelService);
  }

  @Test
  public void findAlleBewerberTest(){
    Adresse adresse = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum = new Date(2000, Calendar.JANUARY, 2);
    Personalien personalien = new Personalien(adresse, "jomu100", "Mueller", "John", geburtsdatum, 20, "Deutschland", "Deutsch");

    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus, studiengangAbschluss);

    Modul modul1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));

    ModulAuswahl modulAuswahl1 = new ModulAuswahl(modul1, 2, 2.0);
    ModulAuswahl modulAuswahl2 = new ModulAuswahl(modul2, 1, 1.7);
    ModulAuswahl modulAuswahl3 = new ModulAuswahl(modul3, 3, 2.3);
    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modulAuswahl1);
    modulAuswahlList.add(modulAuswahl2);
    modulAuswahlList.add(modulAuswahl3);

    BerufModul berufModul = new BerufModul(Beruf.Tutor, modul1);

    Praeferenzen praeferenzen = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul, TutorenSchulungTeilnahme.TEILNAHME);
    List<Dozent> verteiltAn = new LinkedList<Dozent>();
    verteiltAn.add(new Dozent("stephan@hhu.de", "Stephan Mueller"));
    verteiltAn.add(new Dozent("shoetner@hhu.de", "Michael Schoetner"));

    Bewerber bewerber1 = new Bewerber(karriere, personalien, praeferenzen, "Golov", verteiltAn);

    bewerberService.addBewerber(bewerber1);

    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    assertNotNull(bewerberDTOList);
    assertEquals(bewerberDTO1.getPersonalien().getVorname(), bewerber1.getPersonalien().getVorname());

  }

  @Test
  public void findNichtVerteilteBewerberTest(){
    Adresse adresse = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum = new Date(2000, Calendar.JANUARY, 2);
    Personalien personalien = new Personalien(adresse, "jomu100", "Mueller", "John", geburtsdatum, 20, "Deutschland", "Deutsch");

    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus, studiengangAbschluss);

    Modul modul1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));

    ModulAuswahl modulAuswahl1 = new ModulAuswahl(modul1, 2, 2.0);
    ModulAuswahl modulAuswahl2 = new ModulAuswahl(modul2, 1, 1.7);
    ModulAuswahl modulAuswahl3 = new ModulAuswahl(modul3, 3, 2.3);
    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modulAuswahl1);
    modulAuswahlList.add(modulAuswahl2);
    modulAuswahlList.add(modulAuswahl3);

    BerufModul berufModul = new BerufModul(Beruf.Tutor, modul1);

    Praeferenzen praeferenzen = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul, TutorenSchulungTeilnahme.TEILNAHME);
    List<Dozent> verteiltAn = new LinkedList<Dozent>();
    verteiltAn.add(new Dozent("aa","bb"));
    Bewerber bewerber1 = new Bewerber(karriere, personalien, praeferenzen, "Golov", verteiltAn);
    Bewerber bewerber2 = new Bewerber(karriere, personalien, praeferenzen, "lars", null);
    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> nichtVerteilteBewerber = bewerberService.findAlleNichtVerteilteBewerber(bewerberDTOList);
    assertEquals(bewerberDTOList.size(), 2);
    assertEquals(nichtVerteilteBewerber.size(), 1);
  }

  @Test
  public void findAlleVerteilteBewerberTest(){
    Adresse adresse = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum = new Date(2000, Calendar.JANUARY, 2);
    Personalien personalien = new Personalien(adresse, "jomu100", "Mueller", "John", geburtsdatum, 20, "Deutschland", "Deutsch");

    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus, studiengangAbschluss);

    Modul modul1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));

    ModulAuswahl modulAuswahl1 = new ModulAuswahl(modul1, 2, 2.0);
    ModulAuswahl modulAuswahl2 = new ModulAuswahl(modul2, 1, 1.7);
    ModulAuswahl modulAuswahl3 = new ModulAuswahl(modul3, 3, 2.3);
    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modulAuswahl1);
    modulAuswahlList.add(modulAuswahl2);
    modulAuswahlList.add(modulAuswahl3);

    BerufModul berufModul = new BerufModul(Beruf.Tutor, modul1);

    Praeferenzen praeferenzen = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul, TutorenSchulungTeilnahme.TEILNAHME);
    List<Dozent> verteiltAn = new LinkedList<Dozent>();
    verteiltAn.add(new Dozent("aa","bb"));
    Bewerber bewerber1 = new Bewerber(karriere, personalien, praeferenzen, "Golov", verteiltAn);
    Bewerber bewerber2 = new Bewerber(karriere, personalien, praeferenzen, "lars", null);
    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> verteilteBewerber = bewerberService.findAlleVerteilteBewerber(bewerberDTOList);
    assertEquals(bewerberDTOList.size(), 2);
    assertEquals(verteilteBewerber.size(), 1);
  }
  
}
