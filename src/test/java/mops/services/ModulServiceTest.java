package mops.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import mops.domain.database.dto.ModulDTO;
import mops.domain.repositories.ModulRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ModulServiceTest {

  @Mock
  private ModulRepository modulRepository;

  private ModulService modulService;

  @BeforeEach
  void setUp() {
    modulRepository = mock(ModulRepository.class);
    modulService = new ModulService(modulRepository);
  }

  @Test
  void testFindAll() {
    ModulDTO rdb = new ModulDTO("RDB", "rdb@cs.hhu.de", "Conrad");
    ModulDTO ra = new ModulDTO("RA", "ra@cs.hhu.de", "Conrad");
    ModulDTO theo = new ModulDTO("Theoretische Informatik", "info4@cs.hhu.de", "Rothe");

    List<ModulDTO> modulDTOList = Arrays.asList(rdb, ra, theo);

    when(modulRepository.findAll()).thenReturn(modulDTOList);

    List<ModulDTO> foundModules = modulService.findAllModule();

    assertThat(foundModules).isEqualTo(modulDTOList);
  }

  @Test
  void testFindById() {
    ModulDTO theo = new ModulDTO("Theoretische Informatik", "info4@cs.hhu.de", "Rothe");

    when(modulRepository.findModulById(1L)).thenReturn(theo);

    assertThat(modulService.findModulById(1L)).isEqualTo(theo);
  }


  @Test
  void testAddModulListRep() {
    ModulDTO rdb = new ModulDTO("RDB", "rdb@cs.hhu.de", "Conrad");

    List<ModulDTO> modulDTOList = Collections.singletonList(rdb);

    when(modulRepository.save(rdb)).thenReturn(rdb);
    when(modulRepository.findAll()).thenReturn(modulDTOList);

    modulService.addModul(rdb);
    assertThat(modulService.findAllModule()).isEqualTo(modulDTOList);
  }

  @Test
  void testAddModulSolo() {
    ModulDTO rdb = new ModulDTO("RDB", "rdb@cs.hhu.de", "Conrad");

    when(modulRepository.save(rdb)).thenReturn(rdb);
    when(modulRepository.findModulById(0L)).thenReturn(rdb);

    modulService.addModul(rdb);
    assertThat(modulService.findModulById(0L)).isEqualTo(rdb);
  }
}