package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import mops.domain.database.dto.AdresseDTO;
import mops.domain.database.dto.BerufModulDTO;
import mops.domain.database.dto.ModulDTO;
import mops.domain.models.Adresse;
import mops.domain.models.Beruf;
import mops.domain.models.BerufModul;
import mops.domain.models.Dozent;
import mops.domain.models.Modul;
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

  }
}
