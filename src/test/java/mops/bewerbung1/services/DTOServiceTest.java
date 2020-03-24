package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import mops.domain.database.dto.AdresseDTO;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.KarriereDTO;
import mops.domain.database.dto.ModulDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.database.dto.PraeferenzenDTO;
import mops.domain.models.*;
import mops.domain.repositories.ModulRepository;
import mops.domain.services.IDTOService;
import mops.services.DTOService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class DTOServiceTest {

  private transient IDTOService dtoService;
  private transient ModulRepository modulRepo;

  @BeforeEach
  void setUp(){
    modulRepo = mock(ModulRepository.class);
    dtoService = new DTOService(modulRepo);
  }


  @Test
  public void adresseZuAdresseDTO(){
    Adresse adresse = new Adresse("40233","Berlin","Magnumstr","16a");

    AdresseDTO adresseDTO = dtoService.load(adresse);

    assertEquals(adresse.getPLZ(),adresseDTO.getPLZ());
    assertEquals(adresse.getWohnort(),adresseDTO.getWohnort());
    assertEquals(adresse.getStrasse(),adresseDTO.getStrasse());
    assertEquals(adresse.getHausnummer(),adresseDTO.getHausnummer());
  }

  @Test
  public void modulZuModulDTO(){
    Modul modul = new Modul("Propra", new Dozent("propra@cshhu.de","Bendi"));

    ModulDTO modulDTO = dtoService.load(modul);

    assertEquals(modul.getModulName(), modulDTO.getModulName());
    assertEquals(modul.getDozent().getDozentMail(), modulDTO.getDozentMail());
    assertEquals(modul.getModulName(), modulDTO.getModulName());
  }

  @Test
  public void personalienZuPersonalienDTO() throws Exception {
    Adresse adresse = new Adresse("40233","Berlin","Magnumstr","16a");
    Date geburtsDatum = Date.from(new SimpleDateFormat("dd.MM.yyyy",
        Locale.getDefault()).parse("15.07.1999").toInstant());

    Personalien p = new Personalien(adresse,"Kilincarslan","Akin",
        geburtsDatum,24,"Monheim", "Tuerke");

    PersonalienDTO pDTO = dtoService.load(p);

    assertEquals(adresse.getPLZ(),pDTO.getAdresse().getPLZ());
    assertEquals(adresse.getWohnort(),pDTO.getAdresse().getWohnort());
    assertEquals(adresse.getStrasse(),pDTO.getAdresse().getStrasse());
    assertEquals(adresse.getHausnummer(),pDTO.getAdresse().getHausnummer());
    assertEquals(geburtsDatum,pDTO.getGeburtsdatum());
    assertEquals(p.getName(),pDTO.getName());
    assertEquals(p.getVorname(),pDTO.getVorname());
    assertEquals(p.getAlter(),pDTO.getAlter());
    assertEquals(p.getGeburtsort(),pDTO.getGeburtsort());
    assertEquals(p.getNationalitaet(),pDTO.getNationalitaet());
  }

  @Test
  public void karriereZuKarriereDTO(){
    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"KI");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor","HHU");
    Karriere karriere = new Karriere("keine",immartikulationsStatus,studiengangAbschluss);

    KarriereDTO karriereDTO = dtoService.load(karriere);

    assertEquals(karriereDTO.getArbeitserfahrung(),karriere.getArbeitserfahrung());
    assertEquals(karriereDTO.getImmartikulationsStatus().isStatus(),
        karriere.getImmartikulationsStatus().isStatus());
    assertEquals(karriereDTO.getImmartikulationsStatus().getFachrichtung(),
        karriere.getImmartikulationsStatus().getFachrichtung());
    assertEquals(karriereDTO.getFachAbschluss().getStudiengang(),karriere.getFachAbschluss().getStudiengang());
    assertEquals(karriereDTO.getFachAbschluss().getAbschluss(),karriere.getFachAbschluss().getAbschluss());
    assertEquals(karriereDTO.getFachAbschluss().getUni(),karriere.getFachAbschluss().getUni());
  }

  @Test
  public void praeferenzenZuPraeferenDTO(){
    Modul modul1 = new Modul("propra2",new Dozent( "jens@hhu.de", "Jens Bendisposto"));
    Modul modul2 = new Modul("Aldat", new Dozent("stephan@hhu.de", "Stephan Mueller"));
    Modul modul3 = new Modul("RDB", new Dozent("shoetner@hhu.de", "Michael Schoetner"));

    ModulAuswahl modulAuswahl1 = new ModulAuswahl(modul1, 2, 2.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl2 = new ModulAuswahl(modul2, 1, 4.0, Beruf.Korrektor);
    ModulAuswahl modulAuswahl3 = new ModulAuswahl(modul3, 3, 2.7, Beruf.Korrektor);

    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modulAuswahl1);
    modulAuswahlList.add(modulAuswahl2);
    modulAuswahlList.add(modulAuswahl3);

    Praeferenzen praeferenzen = new Praeferenzen(6, 8, modulAuswahlList,
        "No Comment", EinstiegTyp.NEUEINSTIEG, "Keine", TutorenSchulungTeilnahme.TEILNAHME);

    PraeferenzenDTO praeferenzenDTO = dtoService.load(praeferenzen);
    assertNotNull(praeferenzen);

    assertEquals(praeferenzenDTO.getMinWunschStunden(), praeferenzen.getMinWunschStunden());
    assertEquals(praeferenzenDTO.getMaxWunschStunden(), praeferenzen.getMaxWunschStunden());
    assertEquals(praeferenzenDTO.getKommentar(), praeferenzen.getKommentar());
    assertEquals(praeferenzenDTO.getEinstiegTyp(), praeferenzen.getEinstiegTyp());
    assertEquals(praeferenzenDTO.getEinschraenkungen(), praeferenzen.getEinschraenkungen());

    assertEquals(praeferenzenDTO.getModulAuswahl().get(0).getBeruf(), praeferenzen.getModulAuswahl().get(0).getBeruf());
    assertEquals(praeferenzenDTO.getTutorenSchulungTeilnahme(), praeferenzen.getTutorenSchulungTeilnahme());

    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getPrioritaet(),
        praeferenzen.getModulAuswahl().get(1).getPrioritaet());
    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getNote(),
        praeferenzen.getModulAuswahl().get(1).getNote());
    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getModulName(),
        praeferenzen.getModulAuswahl().get(1).getModul().getModulName());
    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentName(),
        praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentName());
    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentMail(),
        praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentMail());
    assertEquals(praeferenzenDTO.getModulAuswahl().get(1).getModul().getDozentMail(),
        praeferenzen.getModulAuswahl().get(1).getModul().getDozent().getDozentMail());
  }

  @Test
  public void bewerberZuBewerberDTO() throws Exception {
    Bewerber b = new Bewerber();
    Personalien personalien = new Personalien();
    personalien.setAlter(18);
    personalien.setGeburtsdatum(
        Date.from(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("31.05.1999").toInstant()));
    personalien.setGeburtsort("Duesseldorf");
    personalien.setName("Winkler");
    personalien.setNationalitaet("Deutschland");
    personalien.setVorname("Marvin");
    personalien.setAdresse(new Adresse("40235", "Duesseldorf", "Porschestraße", "17"));

    Praeferenzen praeferenzen = new Praeferenzen();
    Modul propraModul = new Modul();
    propraModul.setModulName("ProPra");
    propraModul.setDozent(new Dozent("jens@hhu.de", "jens"));

    praeferenzen.setEinschraenkungen("Keine");
    praeferenzen.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
    praeferenzen.setKommentar("Ich mag ProPra");
    praeferenzen.setMaxWunschStunden(14);
    praeferenzen.setMinWunschStunden(7);

    List<ModulAuswahl> modulAuswahl = new ArrayList<>();
    modulAuswahl.add(new ModulAuswahl(propraModul, 2, 1.0, Beruf.Korrektor));

    praeferenzen.setModulAuswahl(modulAuswahl);
    praeferenzen.setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILNAHME);

    Karriere karriere = new Karriere();
    karriere.setArbeitserfahrung("Viel");
    karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
    karriere.setImmartikulationsStatus(new ImmartikulationsStatus(true, "Informatik"));

    b.setPersonalien(personalien);
    b.setPraeferenzen(praeferenzen);
    b.setKarriere(karriere);

    BewerberDTO bewerberDTO = dtoService.load(b);

    assertNotNull(bewerberDTO.getPraeferenzen());
    assertNotNull(bewerberDTO.getPersonalien());
    assertNotNull(bewerberDTO.getKarriere());
    
  }

}

