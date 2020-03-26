package mops.bewerbung1.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.models.Beruf;
import mops.domain.repositories.BewerberRepository;
import mops.domain.repositories.ModulRepository;
import mops.domain.services.IBewerberService;
import mops.services.BewerberService;
import mops.services.DTOService;
import mops.services.ModelService;
import mops.services.ModulService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)



@SpringBootTest

public class BewerberServiceTest {

  private transient IBewerberService bewerberService;
  private transient BewerberRepository bewerberRepository;
  private transient DTOService dtoService;
  private transient ModelService modelService;
  private transient ModulRepository modulRepository;

  @BeforeEach
  void setUp() {
    this.modulRepository = mock(ModulRepository.class);
    this.bewerberRepository = mock(BewerberRepository.class);
    this.dtoService = new DTOService(modulRepository);
    this.modelService = new ModelService();
    this.bewerberService = new BewerberService(bewerberRepository, dtoService, modelService);
  }

  @Test
  public void findAlleBewerberTest(){
    Adresse adresse = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum = new Date(2000, Calendar.JANUARY, 2);
    Personalien personalien = new Personalien(adresse, "Mueller", "John", geburtsdatum, 20, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus, studiengangAbschluss);
    Modul modul1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1 = new ModulAuswahl(modul1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2 = new ModulAuswahl(modul2, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3 = new ModulAuswahl(modul3, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modulAuswahl1);
    modulAuswahlList.add(modulAuswahl2);
    modulAuswahlList.add(modulAuswahl3);
    Praeferenzen praeferenzen = new Praeferenzen(6, 8, modulAuswahlList, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere, personalien, praeferenzen);
    System.out.println(bewerber1);

    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    System.out.println(bewerberDTO1);

    bewerberService.addBewerber(bewerber1);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    assertNotNull(bewerberDTOList);
    assertEquals(bewerberDTOList.get(0).getPersonalien().getVorname(), bewerber1.getPersonalien().getVorname());
  }

  @Test
  public void findNichtVerteilteBewerberTest(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Opladener Strasse", "11");
    Date geburtsdatum2 = new Date(2001, Calendar.JANUARY, 23);
    Personalien personalien2 = new Personalien(adresse2, "Fischer", "Karl", geburtsdatum2, 19, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(false, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("keine.", immartikulationsStatus2, studiengangAbschluss2);
    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 1, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 2, 1.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.0, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);
    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList2, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber2 = new Bewerber(karriere, personalien2, praeferenzen2);

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    VerteilungDTO verteilungDTO1 = new VerteilungDTO("Jens Bendisposto", "jens@hhu.de");
    List<VerteilungDTO> verteilungDTOList = new LinkedList<VerteilungDTO>();
    verteilungDTOList.add(verteilungDTO1);
    bewerberDTO1.setVerteiltAn(verteilungDTOList);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    List<BewerberDTO> alleBewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> nichtVerteilteBewerber = bewerberService.findAlleNichtVerteilteBewerber(alleBewerberDTOList);

    assertEquals(2, alleBewerberDTOList.size());
    assertEquals(1, nichtVerteilteBewerber.size());
  }

  @Test
  public void findAlleVerteilteBewerberTest(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Opladener Strasse", "11");
    Date geburtsdatum2 = new Date(2001, Calendar.JANUARY, 23);
    Personalien personalien2 = new Personalien(adresse2, "Fischer", "Karl", geburtsdatum2, 19, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(false, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("keine.", immartikulationsStatus2, studiengangAbschluss2);
    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 1, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 2, 1.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.0, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);
    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList2, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber2 = new Bewerber(karriere, personalien2, praeferenzen2);

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    VerteilungDTO verteilungDTO1 = new VerteilungDTO("Jens Bendisposto", "jens@hhu.de");
    List<VerteilungDTO> verteilungDTOList = new LinkedList<VerteilungDTO>();
    verteilungDTOList.add(verteilungDTO1);
    bewerberDTO1.setVerteiltAn(verteilungDTOList);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<BewerberDTO> verteilteBewerber = bewerberService.findAlleVerteilteBewerber(bewerberDTOList);

    assertEquals(2, bewerberDTOList.size());
    assertEquals(1, verteilteBewerber.size());
  }
/*
  @Test
  public void findNichtVerteiltTest(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);
    bewerber1.setVerteiltAn(Arrays.asList(new Dozent("Jens Bendisposto", "jens@hhu.de")));

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Opladener Strasse", "11");
    Date geburtsdatum2 = new Date(2001, Calendar.JANUARY, 23);
    Personalien personalien2 = new Personalien(adresse2, "Fischer", "Karl", geburtsdatum2, 19, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(false, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("keine.", immartikulationsStatus2, studiengangAbschluss2);
    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 1, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 2, 1.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.0, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);
    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList2, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber2 = new Bewerber(karriere, personalien2, praeferenzen2);

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);

    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    when(bewerberRepository.findBewerberDTOBByVerteiltAnIsNull()).thenReturn(Arrays.asList(bewerberDTO2));
    List<BewerberDTO> alleBewerberDTOList = bewerberService.findAlleBewerber();
    List<Bewerber> nichtVerteilteBewerber = bewerberService.findNichtVerteilt();
    assertEquals(2, alleBewerberDTOList.size());
    assertEquals(1, nichtVerteilteBewerber.size());
  }
  */
/*
  @Test
  public void findVerteilt(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);
    bewerber1.setVerteiltAn(Arrays.asList(new Dozent("Jens Bendisposto", "jens@hhu.de")));

    Adresse adresse2 = new Adresse("40474", "Duesseldorf", "Opladener Strasse", "11");
    Date geburtsdatum2 = new Date(2001, Calendar.JANUARY, 23);
    Personalien personalien2 = new Personalien(adresse2, "Fischer", "Karl", geburtsdatum2, 19, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus2 = new ImmartikulationsStatus(false, "Informatik");
    StudiengangAbschluss studiengangAbschluss2 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere = new Karriere("keine.", immartikulationsStatus2, studiengangAbschluss2);
    Modul modul1_2 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_2 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_2 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_2 = new ModulAuswahl(modul1_2, 1, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_2 = new ModulAuswahl(modul2_2, 2, 1.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl3_2 = new ModulAuswahl(modul3_2, 3, 2.0, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(modulAuswahl1_2);
    modulAuswahlList2.add(modulAuswahl2_2);
    modulAuswahlList2.add(modulAuswahl3_2);
    Praeferenzen praeferenzen2 = new Praeferenzen(6, 8, modulAuswahlList2, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber2 = new Bewerber(karriere, personalien2, praeferenzen2);

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);

    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    BewerberDTO bewerberDTO2 = dtoService.load(bewerber2);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1, bewerberDTO2));
    when(bewerberRepository.findByVerteiltAnIsNotNull()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<Bewerber> verteilteBewerber = bewerberService.findVerteilt();
    assertEquals(bewerberDTOList.size(), 2);
    assertEquals(verteilteBewerber.size(), 1);
  }
*/

  @Test
  public void findBewerberFuerDozentTest(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);
    bewerberService.addBewerber(bewerber1);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);

    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();
    List<Bewerber> bewerberFuerDozent = bewerberService.findBewerberFuerDozent("stephan@hhu.de");
    assertEquals(1, bewerberDTOList.size());
    assertEquals(1, bewerberFuerDozent.size());
  }

/*
  @Test
  public void verteileTest(){
    Adresse adresse1 = new Adresse("40474", "Duesseldorf", "Amsterdamer Strasse", "2");
    Date geburtsdatum1 = new Date(1996, Calendar.JANUARY, 2);
    Personalien personalien1 = new Personalien(adresse1, "Mueller", "John", geburtsdatum1, 24, "Deutschland", "Deutsch");
    ImmartikulationsStatus immartikulationsStatus1 = new ImmartikulationsStatus(true, "Informatik");
    StudiengangAbschluss studiengangAbschluss1 = new StudiengangAbschluss("Informatik", "Bachelor", "HHU");
    Karriere karriere1 = new Karriere("bei Apple Store gearbeitet.", immartikulationsStatus1, studiengangAbschluss1);
    Modul modul1_1 = new Modul("propra2" , new Dozent( "jens@hhu.de","Jens Bendisposto"));
    Modul modul2_1 = new Modul("Aldat", new Dozent( "stephan@hhu.de", "Stephan Mueller"));
    Modul modul3_1 = new Modul("RDB", new Dozent("shoetner@hhu.de" ,"Michael Schoetner"));
    ModulAuswahl modulAuswahl1_1 = new ModulAuswahl(modul1_1, 2, 2.0, Beruf.Tutor);
    ModulAuswahl modulAuswahl2_1 = new ModulAuswahl(modul2_1, 1, 1.7, Beruf.Tutor);
    ModulAuswahl modulAuswahl3_1 = new ModulAuswahl(modul3_1, 3, 2.3, Beruf.Tutor);
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(modulAuswahl1_1);
    modulAuswahlList1.add(modulAuswahl2_1);
    modulAuswahlList1.add(modulAuswahl3_1);
    Praeferenzen praeferenzen1 = new Praeferenzen(6, 8, modulAuswahlList1, "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);
    Bewerber bewerber1 = new Bewerber(karriere1, personalien1, praeferenzen1);

    Dozent dozent = new Dozent("jens@hhu.de","Jens Bendisposto");
    String id = "jomu100";

    bewerberService.addBewerber(bewerber1);
    BewerberDTO bewerberDTO1 = dtoService.load(bewerber1);
    when(bewerberRepository.findAll()).thenReturn(Arrays.asList(bewerberDTO1));
    when(bewerberRepository.findBewerberByKennung("jomu@hhu.de")).thenReturn(bewerberDTO1);
//    when(bewerberRepository.findById(id)).thenReturn(Optional.of(bewerberDTO1));
    when(bewerberRepository.save(bewerberDTO1)).thenReturn(bewerberDTO1);
    List<BewerberDTO> bewerberDTOList = bewerberService.findAlleBewerber();

    bewerberService.verteile(id, dozent);

    assertEquals(bewerberDTOList.get(0).getKennung(), "jomu100");
    assertEquals(bewerberDTOList.get(0).getVerteiltAn().get(0).getDozentKennung(), "jens@hhu.de");
  }*/
}
