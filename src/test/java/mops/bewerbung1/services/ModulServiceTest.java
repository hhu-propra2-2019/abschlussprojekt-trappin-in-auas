
package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import mops.bewerbung1.testutils.ModelGenerator;
import mops.domain.database.dto.ModulDTO;
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

    modulService.addModul(modelGenerator.generateModul());
    assertEquals(1, pseudoDatenbank.size());
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
  public void test(){
    modulService.findAllModule();
  }

  private void removeAllDTOMock(List<ModulDTO> pseudoDatenbank) {
    doAnswer(invocation -> {
      pseudoDatenbank.clear();
      return null;
    }).when(modulRepository).deleteAll();
  }
}