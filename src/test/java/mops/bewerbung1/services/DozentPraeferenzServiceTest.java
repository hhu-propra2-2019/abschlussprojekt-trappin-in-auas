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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DozentPraeferenzServiceTest {
  private final String BEWERBER = "bewerber";
  private final String DOZENT_MAIL = "dozentMail";
  private final int PRAEFERENZ = 3;
  DozentPraeferenzDTO addDozentPraeferenzDTO;

  @Autowired
  private transient IDozentPraeferenzService dozentPraeferenzService;

  @BeforeEach
  public void buildDTO(){
    addDozentPraeferenzDTO = new DozentPraeferenzDTO(BEWERBER, DOZENT_MAIL, PRAEFERENZ);
  }



  @Test
  public void addDozentPraeverenz(){
    DozentPraeferenzRepo dozentPraeferenzRepoMock = mock(DozentPraeferenzRepo.class);
    IDozentPraeferenzService serviceWithMockRepo = new DozentPraeferenzService(dozentPraeferenzRepoMock);
    DozentPraeferenzDTO dozentPraeferenzDTO = new DozentPraeferenzDTO("bewerber","dozentMail", 3);

    serviceWithMockRepo.addPraeferenz(dozentPraeferenzDTO);

    verify(dozentPraeferenzRepoMock, times(1)).save(dozentPraeferenzDTO);
  }

  @Test
  public void readPraeferenzFromDb(){
    dozentPraeferenzService.addPraeferenz(addDozentPraeferenzDTO);
    int dozentPraeferenz = dozentPraeferenzService.getDozentPraeferenz(BEWERBER, DOZENT_MAIL);

    assertThat(dozentPraeferenz).isEqualTo(PRAEFERENZ);

  }

  @Test
  public void deletePraeferenzFromDb(){
    dozentPraeferenzService.addPraeferenz(addDozentPraeferenzDTO);
    dozentPraeferenzService.deletePraeferenz(BEWERBER, DOZENT_MAIL);
    Integer readDozentPraeferenzDTO = dozentPraeferenzService.getDozentPraeferenz(BEWERBER, DOZENT_MAIL);

    assertThat(readDozentPraeferenzDTO).isEqualTo(-1);
  }

  @Test
  public void testBooleanAlreadyConfirmed(){
    dozentPraeferenzService.addPraeferenz(addDozentPraeferenzDTO);
    boolean alreadyConfirmed = dozentPraeferenzService.alreadyConfirmed(BEWERBER, DOZENT_MAIL);

    assertThat(alreadyConfirmed).isEqualTo(true);
  }
}
