package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.database.dto.VerteilungDTO;
import mops.domain.models.Dozent;
import mops.domain.models.Modul;
import mops.domain.models.ModuleMitVerteiltenAnzahl;
import mops.domain.repositories.VerteilungRepo;
import mops.services.VerteilerService;

@SpringBootTest
public class VerteilerServiceTest {

  @Mock
  private transient VerteilungRepo verteilungRepo;

  private transient VerteilerService verteilerService;

  @BeforeEach
  public void setUp() {
    verteilerService = new VerteilerService(verteilungRepo);
  }

  @Test
  public void buildModulMitVerteiltenAnzahlTest() {
    when(verteilungRepo.countVerteilungDTOByDozentKennungEquals("chmet220")).thenReturn(2);

    List<Modul> module = Arrays.asList(new Modul("Propra", new Dozent("chmet220", "Christian Meter")));
    List<ModuleMitVerteiltenAnzahl> modulMitVerteilten = verteilerService.getListModulMitAnzahlVerteilten(module);

    assertNotNull(modulMitVerteilten);
    assertEquals(1, modulMitVerteilten.size());
    System.out.println(modulMitVerteilten);
  }
}