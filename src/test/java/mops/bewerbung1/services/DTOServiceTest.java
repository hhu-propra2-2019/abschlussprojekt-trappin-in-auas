package mops.bewerbung1.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import mops.bewerbung1.testutils.ModelDTOCompare;
import mops.bewerbung1.testutils.ModelGenerator;
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
  private transient ModelGenerator modelGenerator;

  @BeforeEach
  void setUp(){
    modulRepo = mock(ModulRepository.class);
    dtoService = new DTOService(modulRepo);
    modelDTOCompare = new ModelDTOCompare();
    modelGenerator = new ModelGenerator();
  }


  @Test
  public void adresseZuAdresseDTO(){
    Adresse adresse = modelGenerator.generateAdresse();
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
    Personalien personalien = modelGenerator.generatePersonalien();
    PersonalienDTO personalienDTO = dtoService.load(personalien);

    modelDTOCompare.compare(personalien, personalienDTO);
  }

  @Test
  public void karriereZuKarriereDTO(){
    Karriere karriere = modelGenerator.generateKarriere();
    KarriereDTO karriereDTO = dtoService.load(karriere);

    modelDTOCompare.compare(karriere, karriereDTO);
  }

  @Test
  public void praeferenzenZuPraeferenDTO(){
    List<ModulAuswahl> modulAuswahlList = new LinkedList<ModulAuswahl>();
    modulAuswahlList.add(modelGenerator.generateModulAuswahl());
    modulAuswahlList.add(modelGenerator.generateModulAuswahl());
    modulAuswahlList.add(modelGenerator.generateModulAuswahl());

    Praeferenzen praeferenzen = modelGenerator.generatePraeferenz();
    praeferenzen.setModulAuswahl(modulAuswahlList);
    PraeferenzenDTO praeferenzenDTO = dtoService.load(praeferenzen);

    assertNotNull(praeferenzen);
    modelDTOCompare.compare(praeferenzen, praeferenzenDTO);
  }

  @Test
  public void bewerberZuBewerberDTO() throws Exception {
    Bewerber b = modelGenerator.generateBewerber();

    List<ModulAuswahl> modulAuswahl = new ArrayList<>();
    modulAuswahl.add(modelGenerator.generateModulAuswahl());

    b.getPraeferenzen().setModulAuswahl(modulAuswahl);
    b.getPraeferenzen().setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILNAHME);

    BewerberDTO bewerberDTO = dtoService.load(b);

    assertNotNull(bewerberDTO.getPraeferenzen());
    assertNotNull(bewerberDTO.getPersonalien());
    assertNotNull(bewerberDTO.getKarriere());
    
    modelDTOCompare.compare(b, bewerberDTO);
  }

  @Test
  public void bewerberZuBewerberDTOWithDozentPrefsAndVerteilung() throws Exception {
    Bewerber b = modelGenerator.generateBewerber();

    List<ModulAuswahl> modulAuswahl = new ArrayList<>();
    modulAuswahl.add(modelGenerator.generateModulAuswahl());

    b.getPraeferenzen().setModulAuswahl(modulAuswahl);
    b.getPraeferenzen().setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILNAHME);
    b.setDozentPraeferenz(Arrays.asList(new DozentPraeferenz("bla", "bla", 2)));
    b.setVerteiltAn(Arrays.asList(new Dozent("y", "asd")));
    BewerberDTO bewerberDTO = dtoService.load(b);

    assertNotNull(bewerberDTO.getPraeferenzen());
    assertNotNull(bewerberDTO.getPersonalien());
    assertNotNull(bewerberDTO.getKarriere());
    
    modelDTOCompare.compare(b, bewerberDTO);
  }

}

