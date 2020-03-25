package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mops.bewerbung1.testutils.ModelDTOCompare;
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
  private transient ModelDTOCompare modelDTOCompare;

  @BeforeEach
  void setUp(){
    modulRepo = mock(ModulRepository.class);
    dtoService = new DTOService(modulRepo);
    modelDTOCompare = new ModelDTOCompare();
  }


  @Test
  public void adresseZuAdresseDTO(){
    Adresse adresse = new Adresse("40233","Berlin","Magnumstr","16a");
    AdresseDTO adresseDTO = dtoService.load(adresse);

    modelDTOCompare.compare(adresse, adresseDTO);
  }

  @Test
  public void modulZuModulDTO(){
    Modul modul = new Modul("Propra", new Dozent("propra@cshhu.de","Bendi"));
    ModulDTO modulDTO = dtoService.load(modul);

    modelDTOCompare.compare(modul, modulDTO);
  }

  @Test
  public void personalienZuPersonalienDTO() throws Exception {
    Adresse adresse = new Adresse("40233","Berlin","Magnumstr","16a");
    Date geburtsDatum = Date.from(new SimpleDateFormat("dd.MM.yyyy",
        Locale.getDefault()).parse("15.07.1999").toInstant());

    Personalien p = new Personalien(adresse,"Kilincarslan","Akin",
        geburtsDatum,24,"Monheim", "Tuerke");

    PersonalienDTO pDTO = dtoService.load(p);

    modelDTOCompare.compare(p, pDTO);
  }

  @Test
  public void karriereZuKarriereDTO(){
    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"KI");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor","HHU");
    
    Karriere karriere = new Karriere("keine",immartikulationsStatus,studiengangAbschluss);
    KarriereDTO karriereDTO = dtoService.load(karriere);

    modelDTOCompare.compare(karriere, karriereDTO);
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

    modelDTOCompare.compare(praeferenzen, praeferenzenDTO);
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
    
    modelDTOCompare.compare(b, bewerberDTO);
  }

}

