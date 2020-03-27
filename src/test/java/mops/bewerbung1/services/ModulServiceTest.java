
package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mops.bewerbung1.testutils.DTOGenerator;
import mops.bewerbung1.testutils.ModelGenerator;
import mops.domain.database.dto.ModulDTO;
import mops.domain.models.Dozent;
import mops.domain.models.Modul;
import mops.domain.repositories.ModulRepository;
import mops.services.DTOService;
import mops.services.ModelService;
import mops.services.ModulService;

@SpringBootTest
public class ModulServiceTest {

  private transient ModulService modulService;
  private transient ModelService modelService;
  private transient ModelGenerator modelGenerator;
  private transient DTOService dtoService;

  @Mock
  private transient ModulRepository modulRepository;
  
  @BeforeEach
  public void setUp(){
    this.dtoService = new DTOService(modulRepository);
    this.modelGenerator = new ModelGenerator();
    this.modelService = new ModelService();
    this.modulService = new ModulService(modulRepository, modelService, dtoService);
  }
  
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private void addModulDTOMock(List<ModulDTO> pseudoDatenbank){
    doAnswer(invocation -> {
      ModulDTO modulDTO = (ModulDTO) invocation.getArguments()[0];
      if(pseudoDatenbank.stream().anyMatch(x -> x.getModulName().equals(modulDTO.getModulName()))){
        ModulDTO gefundene = pseudoDatenbank.stream().filter(x -> x.getModulName().equals(modulDTO.getModulName())).findFirst().get();
        pseudoDatenbank.remove(gefundene);
      }
      pseudoDatenbank.add(modulDTO);
      return null;
    }).when(modulRepository).save(any(ModulDTO.class));
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private void removeModulDTOMock(List<ModulDTO> pseudoDatenbank) {
    doAnswer(invocation -> {
      pseudoDatenbank.removeIf(x -> x.getModulName().equals((String) invocation.getArguments()[0]));
      return null;
    }).when(modulRepository).deleteModulByName(any(String.class));
  }

  @Test
  public void addModulTest(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();

    addModulDTOMock(pseudoDatenbank);
    Modul modul = modelGenerator.generateModul();
    modulService.addModul(modul);
    modulService.addModul(modul);
    assertEquals(1, pseudoDatenbank.size());
  }

  @Test
  public void modulExistsTest(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();
    DTOGenerator gen = new DTOGenerator();
    ModulDTO modul1 = gen.generateModul();
    modul1.setDozentMail("uniquemail1");
    modul1.setModulName("uniquemodul1");
    ModulDTO modul2 = gen.generateModul();
    modul2.setDozentMail("uniquemail2");
    modul2.setModulName("uniquemodul2");

    Modul modulmodel1 = modelGenerator.generateModul();
    Modul modulmodel2 = modelGenerator.generateModul();
    modulmodel1.setModulName("uniquemodul1");
    modulmodel1.setDozent(new Dozent("uniquemail1", "name"));
    modulmodel1.setModulName("uniquemodul2");
    modulmodel1.setDozent(new Dozent("uniquemail2", "name"));

    addModulDTOMock(pseudoDatenbank);
    when(modulRepository.findByModulNameAndDozentMail("uniquemodul1", "uniquemail1")).thenReturn(Arrays.asList(modul1));
    when(modulRepository.findByModulNameAndDozentMail("uniquemodul2", "uniquemail2")).thenReturn(Arrays.asList(modul2));

    modulService.addModul(modulmodel1);
    
    assertEquals(true, modulService.modulExists(modulmodel1));
    assertEquals(false, modulService.modulExists(modulmodel2));
    assertEquals(false, modulService.modulExists(null));
  }

  @Test
  public void findModulByName(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();
    DTOGenerator gen = new DTOGenerator();
    ModulDTO modul1 = gen.generateModul();
    String modulname = "uniquemodul3";
    modul1.setModulName(modulname);

    Modul modulmodel1 = modelGenerator.generateModul();
    modulmodel1.setModulName(modulname);

    addModulDTOMock(pseudoDatenbank);
    when(modulRepository.findModulByModulName(modulname)).thenReturn((modul1));

    modulService.addModul(modulmodel1);
    
    Modul gefunden = modulService.findModulByModulName(modulname);

    assertEquals(modulmodel1.getModulName(), gefunden.getModulName());
  }

  @Test
  public void removeModulTest(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();
    Modul testModul = modelGenerator.generateModul();
    pseudoDatenbank.add(dtoService.load(testModul));

    removeModulDTOMock(pseudoDatenbank);

    modulService.deleteModulByName(testModul.getModulName());
    assertEquals(0, pseudoDatenbank.size());
  }

  @Test
  public void removeAllModulTest(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();
    Modul testModul = modelGenerator.generateModul();
    Modul testModul2 = modelGenerator.generateModul();
    Modul testModul3 = modelGenerator.generateModul();
    pseudoDatenbank.add(dtoService.load(testModul));
    pseudoDatenbank.add(dtoService.load(testModul2));
    pseudoDatenbank.add(dtoService.load(testModul3));

    removeAllDTOMock(pseudoDatenbank);

    modulService.deleteAll();
    assertEquals(0, pseudoDatenbank.size());
  }

  @Test
  public void testFindAll(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();
    Modul testModul = modelGenerator.generateModul();
    Modul testModul2 = modelGenerator.generateModul();
    Modul testModul3 = modelGenerator.generateModul();
    pseudoDatenbank.add(dtoService.load(testModul));
    pseudoDatenbank.add(dtoService.load(testModul2));
    pseudoDatenbank.add(dtoService.load(testModul3));

    when(modulRepository.findAll()).thenReturn(pseudoDatenbank);

    List<Modul> module = modulService.findAllModule();
    assertEquals(3, module.size());
  }

  private void removeAllDTOMock(List<ModulDTO> pseudoDatenbank) {
    doAnswer(invocation -> {
      pseudoDatenbank.clear();
      return null;
    }).when(modulRepository).deleteAll();
  }
}