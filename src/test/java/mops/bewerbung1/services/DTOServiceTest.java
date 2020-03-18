package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.PraeferenzenDTO;
import mops.domain.models.*;
import mops.services.DTOService;

@SpringBootTest
public class DTOServiceTest {
  private transient DTOService dtoService;

  @BeforeEach
  public void initialization() {
    dtoService = new DTOService();
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
    personalien.setUnikennung("mawin125");
    personalien.setVorname("Marvin");
    personalien.setAdresse(new Adresse("40235", "Duesseldorf", "Porschestraße", "17"));

    Praeferenzen praeferenzen = new Praeferenzen();
    Modul propraModul = new Modul();
    propraModul.setModulName("ProPra");
    propraModul.setDozent(new Dozent("jens@hhu.de", "jens"));

    praeferenzen.setBerufModul(new BerufModul(Beruf.Korrektor, propraModul));
    praeferenzen.setEinschraenkungen("Keine");
    praeferenzen.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
    praeferenzen.setKommentar("Ich mag ProPra");
    praeferenzen.setMaxWunschStunden(14);
    praeferenzen.setMinWunschStunden(7);

    List<ModulAuswahl> modulAuswahl = new ArrayList<>();
    modulAuswahl.add(new ModulAuswahl(propraModul, 2, 1.0));

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

  @Test
  public void praeferenzenZuDTO() {
    Modul propra = new Modul("propra", new Dozent("jens@hhu.de", "jens"));
    ModulAuswahl propraAuswahl = new ModulAuswahl();
    propraAuswahl.setModul(propra);
    propraAuswahl.setNote(1.0);
    propraAuswahl.setPrioritaet(2);

    List<ModulAuswahl> modulAuswahls = new ArrayList<>();
    modulAuswahls.add(propraAuswahl);

    Praeferenzen pref = new Praeferenzen(14, 10, modulAuswahls, "irgendwas", EinstiegTyp.NEUEINSTIEG, "keine",
        new BerufModul(Beruf.Korrektor, propra), TutorenSchulungTeilnahme.TEILNAHME);
    
    PraeferenzenDTO prefDTO = dtoService.load(pref);
    assertNotNull(prefDTO);
  }
}