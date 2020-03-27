package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import mops.bewerbung1.testutils.DTOGenerator;
import mops.bewerbung1.testutils.ModelDTOCompare;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.services.IModelService;
import mops.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class ModelServiceTest {

  private transient IModelService modelService;
  private transient DTOGenerator dtoGenerator;
  private transient ModelDTOCompare modelDTOCompare;

  @BeforeEach
  void setUp() {
    modelService = new ModelService();
    this.dtoGenerator = new DTOGenerator();
    this.modelDTOCompare = new ModelDTOCompare();
  }

  @Test
  public void bewerberDTOListToBewerberList(){
    List<BewerberDTO> bewerberDTOs = new LinkedList<>();
    bewerberDTOs.add(dtoGenerator.generateBewerber());
    bewerberDTOs.add(dtoGenerator.generateBewerber());
    bewerberDTOs.add(dtoGenerator.generateBewerber());

    List<Bewerber> bewerbers = modelService.loadBewerberList(bewerberDTOs);

    for(int i = 0; i < bewerberDTOs.size(); i++){
      modelDTOCompare.compare(bewerbers.get(i), bewerberDTOs.get(i));
    }
  }

  @Test
  public void bewerberDTOWithDozentPref(){
    
  }

  @Test
  public void nullbewerberdtoZuBewerber(){
    BewerberDTO bewerberDTO = null;
    Bewerber bewerber = modelService.load(bewerberDTO);

    assertNull(bewerber);
  }

  @Test
  public void dozentPraeferenzDTOzuDozentPraeferenz(){
    DozentPraeferenzDTO dPraeferenzDTO = dtoGenerator.generateDozentPraeferenz();
    DozentPraeferenz dPraeferenz = modelService.load(dPraeferenzDTO);
    
    modelDTOCompare.compare(dPraeferenz, dPraeferenzDTO);
  }

  @Test
  public void personalienDTOZuPersonalienModel() {
    PersonalienDTO personalienDTO = dtoGenerator.generatePersonalien();
    try{
      personalienDTO.setGeburtsdatum(
          Date.from(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("15.07.1999").toInstant()));
    } catch (Exception e) {
      personalienDTO.setGeburtsdatum(null);
    }

    Personalien personalien = modelService.load(personalienDTO);

    modelDTOCompare.compare(personalien, personalienDTO);
  }

  @Test
  public void personalienDTOIsNullMappingReturnsNull() {
    PersonalienDTO personalienDTO = null;
    Personalien personalien = modelService.load(personalienDTO);

    assertNull(personalien);
  }

  @Test
  public void karriereDTOIsNullTest() {
    KarriereDTO karriereDTO = null;
    Karriere karriere = modelService.load(karriereDTO);
    assertNull(karriere);
  }

  @Test
  public void karriereDTOzuKarriereModel() {
    KarriereDTO karriereDTO = dtoGenerator.generateKarriere();
    Karriere karriere = modelService.load(karriereDTO);

    modelDTOCompare.compare(karriere, karriereDTO);
  }

  @Test
  public void modulAuswahlIsNull() {
    ModulAuswahlDTO modulAuswahlDTO = null;
    ModulAuswahl modulAuswahl = modelService.load(modulAuswahlDTO);
    assertNull(modulAuswahl);
  }

  @Test
  public void modulAuswahlDTOzuModulAuswahl() {
    ModulAuswahlDTO modulAuswahlDTO = dtoGenerator.generateModulAuswahl();

    ModulAuswahl modulAuswahl = modelService.load(modulAuswahlDTO);

    modelDTOCompare.compare(modulAuswahl, modulAuswahlDTO);
  }

  @Test
  public void praeferenzenDTOzuPraeferenzen() {
    PraeferenzenDTO praeferenzenDTO = dtoGenerator.generatePraeferenz();
    Praeferenzen praeferenzen = modelService.load(praeferenzenDTO);

    modelDTOCompare.compare(praeferenzen, praeferenzenDTO);
  }

  @Test
  public void abschlussDTOzuAbschlussTest() {
    StudiengangAbschlussDTO studiengangAbschlussDTO = dtoGenerator.generateAbschluss();

    StudiengangAbschluss studiengangAbschluss = modelService.load(studiengangAbschlussDTO);

    modelDTOCompare.compare(studiengangAbschluss, studiengangAbschlussDTO);
  }

  @Test
  public void loadModulListTest() {
    List<ModulDTO> modulDTOList = new LinkedList<ModulDTO>();

    modulDTOList.add(dtoGenerator.generateModul());
    modulDTOList.add(dtoGenerator.generateModul());
    modulDTOList.add(dtoGenerator.generateModul());
    modulDTOList.add(dtoGenerator.generateModul());
    modulDTOList.add(dtoGenerator.generateModul());

    List<Modul> modulList = modelService.loadModulList(modulDTOList);

    for(int i = 0; i < modulDTOList.size(); i++){
      modelDTOCompare.compare(modulList.get(i), modulDTOList.get(i));
    }
  }

}