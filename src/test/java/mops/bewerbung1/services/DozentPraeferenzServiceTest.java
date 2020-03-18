package mops.bewerbung1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import mops.domain.database.dto.DozentPraeferenzDTO;
import mops.domain.repositories.DozentPraeferenzRepo;
import mops.domain.services.IDozentPraeferenzService;
import mops.services.DozentPraeferenzService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DozentPraeferenzServiceTest {

  private IDozentPraeferenzService dozentPraeferenzService;

  @BeforeEach
  public void initialization() {
    dozentPraeferenzService = new DozentPraeferenzService();
  }


  @Test
  public void addDozentPraeverenz(){
    DozentPraeferenzRepo dozentPraeferenzRepoMock = mock(DozentPraeferenzRepo.class);
    DozentPraeferenzDTO dozentPraeferenzDTO = new DozentPraeferenzDTO("bewerber","dozentMail", 3);

    dozentPraeferenzService.addPraeferenz(dozentPraeferenzDTO);

    verify(dozentPraeferenzRepoMock, times(1)).save(dozentPraeferenzDTO);
  }

}
