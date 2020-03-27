
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
  private void removeBewerberDTOMock(List<ModulDTO> pseudoDatenbank) {
    doAnswer(invocation -> {
      pseudoDatenbank.remove((ModulDTO) invocation.getArguments()[0]);
      return null;
    }).when(modulRepository).delete(any(ModulDTO.class));
  }

  @Test
  public void addModulTest(){
    List<ModulDTO> pseudoDatenbank = new ArrayList<>();

    addModulDTOMock(pseudoDatenbank);
    removeBewerberDTOMock(pseudoDatenbank);

    modulService.addModul(modelGenerator.generateModul());
    assertEquals(1, pseudoDatenbank.size());
  }
}