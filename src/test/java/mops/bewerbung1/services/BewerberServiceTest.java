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
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

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

  @Test
  public void findNichtVerteilt(){
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

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum2 = new Date(2000, Calendar.JANUARY, 2);
    Personalien personalien2 = new Personalien(adresse2, "jomu100", "Mueller", "John", geburtsdatum2, 20, "Deutschland", "Deutsch");

    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere2 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus2, studiengangAbschluss2);

    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));

    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 2, 2.0);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 1, 1.7);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.3);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);

    BerufModul berufModul2 = new BerufModul(Beruf.Tutor, modul1_2);

    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul2, TutorenSchulungTeilnahme.TEILNAHME);
    List<Dozent> verteiltAn2 = new LinkedList<Dozent>();
    verteiltAn2.add(new Dozent("aa","bb"));

    Bewerber bewerber1 = new Bewerber(karriere2, personalien2, praeferenzen2, "Golov", verteiltAn2);
    Bewerber bewerber2 = new Bewerber(karriere, personalien, praeferenzen, "lars", null);
    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    when(bewerberRepository.findByVerteiltAnIsNotNull()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> verteilteBewerber = bewerberService.findVerteilt();
    assertEquals(bewerberDTOList.size(), 2);
    assertEquals(verteilteBewerber.size(), 1);
  }

  @Test
  public void findVerteilt(){
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

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Uerdinger Strasse", "3");
    Date geburtsdatum2 = new Date(2001, Calendar.JANUARY, 4);
    Personalien personalien2 = new Personalien(adresse2, "kafi100", "Fischer", "Karl", geburtsdatum2, 20, "Deutschland", "Deutsch");

    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere2 = new Karriere("keine.", immartikulationsStatus2, studiengangAbschluss2);

    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));

    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 3, 2.0);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 1, 1.0);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.3);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);

    BerufModul berufModul2 = new BerufModul(Beruf.Tutor, modul1_2);

    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", berufModul2, TutorenSchulungTeilnahme.TEILNAHME);
    List<Dozent> verteiltAn2 = new LinkedList<Dozent>();
    verteiltAn2.add(new Dozent("aa","bb"));

    Bewerber bewerber1 = new Bewerber(karriere2, personalien2, praeferenzen2, "Golov", verteiltAn2);
    Bewerber bewerber2 = new Bewerber(karriere, personalien, praeferenzen, "lars", null);
    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    when(bewerberRepository.findByVerteiltAnIsNull()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> verteilteBewerber = bewerberService.findNichtVerteilt();
    assertEquals(bewerberDTOList.size(), 2);
    assertEquals(verteilteBewerber.size(), 1);
  }

  @Test
  public void findBewerberFuerDozentTest(){
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

    bewerberService.addBewerber(bewerber1);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<Bewerber> bewerberFuerDozent = bewerberService.findBewerberFuerDozent("stephan@hhu.de");
    assertEquals(bewerberDTOList.get(0).getPersonalien().getUnikennung(), "jomu100");
    assertEquals(bewerberFuerDozent.get(0).getPersonalien().getUnikennung(), "jomu100");
  }

  @Test
  public void verteileTest(){
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
    Bewerber bewerber1 = new Bewerber(karriere, personalien, praeferenzen, "Golov", verteiltAn);
    Dozent dozent = new Dozent("jens@hhu.de","Jens Bendisposto");
    String id = "jomu100";

    bewerberService.addBewerber(bewerber1);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    when(bewerberRepository.findById(id)).thenReturn(Optional.of(bewerberDTO1));
    when(bewerberRepository.save(bewerberDTO1)).thenReturn(bewerberDTO1);
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();

    bewerberService.verteile(id, dozent);

    assertEquals(bewerberDTOList.get(0).getPersonalien().getUnikennung(), "jomu100");
    assertEquals(bewerberDTOList.get(0).getVerteiltAn().get(0).getDozentKennung(), "jens@hhu.de");
  }
}
