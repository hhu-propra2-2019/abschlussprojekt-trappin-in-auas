package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import mops.domain.database.dto.AdresseDTO;
import mops.domain.database.dto.BerufModulDTO;
import mops.domain.database.dto.KarriereDTO;
import mops.domain.database.dto.ModulDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.models.*;

import mops.domain.services.IDTOService;
import mops.services.DTOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DTOServiceTest {

  private transient IDTOService dtoService;

  @BeforeEach
  void setup(){
    dtoService = new DTOService();
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
    Modul modul = new Modul("Propra",new Dozent("propra@cshhu.de","Bendi"));

    ModulDTO modulDTO = dtoService.load(modul);

    assertEquals(modul.getModulName(),modulDTO.getModulName());
    assertEquals(modul.getDozent().getDozentMail(),modulDTO.getDozentMail());
    assertEquals(modul.getModulName(),modulDTO.getModulName());
  }

  @Test
  public void berufModulZuBerufModul(){
    Modul modul = new Modul("Propra",new Dozent("propra@cshhu.de","Bendi"));
    BerufModul berufModul = new BerufModul(Beruf.Korrektor,modul);

    BerufModulDTO berufModulDTO = dtoService.load(berufModul);

    assertEquals(berufModulDTO.getBeruf(),berufModul.getBeruf());
    assertEquals(modul.getModulName(),berufModulDTO.getModul().getModulName());
    assertEquals(modul.getDozent().getDozentMail(),berufModulDTO.getModul().getDozentMail());
    assertEquals(modul.getModulName(),berufModulDTO.getModul().getModulName());
  }

  @Test
  public void personalienZuPersonalienDTO(){
    Adresse adresse = new Adresse("40233","Berlin","Magnumstr","16a");
    Date geburtsdatum = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
    String datum = "24.05.1996";
    try {
      geburtsdatum = ft.parse(datum);
    } catch (Exception ignored) {

    }
    Personalien p = new Personalien(adresse,"akkil100","Kilincarslan","Akin",
        geburtsdatum,24,"Monheim", "Tuerke");

    PersonalienDTO pDTO = dtoService.load(p);

    assertEquals(adresse.getPLZ(),pDTO.getAdresse().getPLZ());
    assertEquals(adresse.getWohnort(),pDTO.getAdresse().getWohnort());
    assertEquals(adresse.getStrasse(),pDTO.getAdresse().getStrasse());
    assertEquals(adresse.getHausnummer(),pDTO.getAdresse().getHausnummer());
    assertEquals(geburtsdatum,pDTO.getGeburtsdatum());
    assertEquals(p.getUnikennung(),pDTO.getUnikennung());
    assertEquals(p.getName(),pDTO.getName());
    assertEquals(p.getVorname(),pDTO.getVorname());
    assertEquals(p.getAlter(),pDTO.getAlter());
    assertEquals(p.getGeburtsort(),pDTO.getGeburtsort());
    assertEquals(p.getNationalitaet(),pDTO.getNationalitaet());
  }

  @Test
  public void karriereZuKarriereDTO(){
    ImmartikulationsStatus immartikulationsStatus = new ImmartikulationsStatus(true,"Informatik");
    StudiengangAbschluss studiengangAbschluss = new StudiengangAbschluss("Informatik","Bachelor");
    Karriere karriere = new Karriere("keine",immartikulationsStatus,studiengangAbschluss);

    KarriereDTO karriereDTO = dtoService.load(karriere);

    assertEquals(karriereDTO.getArbeitserfahrung(),karriere.getArbeitserfahrung());
    assertEquals(karriereDTO.getImmartikulationsStatus().isStatus(),
        karriere.getImmartikulationsStatus().isStatus());
    assertEquals(karriereDTO.getImmartikulationsStatus().getFachrichtung(),
        karriere.getImmartikulationsStatus().getFachrichtung());
    assertEquals(karriereDTO.getFachAbschluss().getStudiengang(),karriere.getFachAbschluss().getStudiengang());
    assertEquals(karriereDTO.getFachAbschluss().getAbschluss(),karriere.getFachAbschluss().getAbschluss());
  }

  @Test
  public void praeferenzenZuPraeferenDTO(){
    Praeferenzen prf = new Praeferenzen();
  }
}
