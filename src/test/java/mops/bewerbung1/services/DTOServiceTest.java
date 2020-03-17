package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import mops.domain.database.dto.AdresseDTO;
import mops.domain.models.Adresse;
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
  public void personalienZuPersonalienDTO(){

  }
}
