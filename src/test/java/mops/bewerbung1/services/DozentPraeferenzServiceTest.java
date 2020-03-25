package mops.bewerbung1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import mops.domain.database.dto.DozentPraeferenzDTO;
import mops.domain.services.IDozentPraeferenzService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@Disabled //braucht Bewerber eintrag in DB
@SpringBootTest
public class DozentPraeferenzServiceTest {
  private transient final String BEWERBER = "bewerber";
  private transient final String DOZENT_MAIL = "dozentMail";
  private transient final int PRAEFERENZ = 3;
  transient DozentPraeferenzDTO addDozentPraeferenzDTO;

  @Autowired
  private transient IDozentPraeferenzService dozentPraeferenzService;

  @BeforeEach
  public void buildDTO(){
    addDozentPraeferenzDTO = new DozentPraeferenzDTO(BEWERBER, DOZENT_MAIL, PRAEFERENZ);
    dozentPraeferenzService.deletePraeferenz(BEWERBER, DOZENT_MAIL);
  }




/*
  @Disabled //DozentPraeferenzRepo wird nicht mehr genutzt
  @Test
  public void addDozentPraeverenz(){
    DozentPraeferenzRepo dozentPraeferenzRepoMock = mock(DozentPraeferenzRepo.class);
    IDozentPraeferenzService serviceWithMockRepo = new DozentPraeferenzService(dozentPraeferenzRepoMock);
    DozentPraeferenzDTO dozentPraeferenzDTO = new DozentPraeferenzDTO("bewerber","dozentMail", 3);

    serviceWithMockRepo.addPraeferenz(dozentPraeferenzDTO);

    verify(dozentPraeferenzRepoMock, times(1)).save(dozentPraeferenzDTO);
  }
  */




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
  public void testBooleanAlreadyConfirmed_true(){
    dozentPraeferenzService.addPraeferenz(addDozentPraeferenzDTO);
    boolean alreadyConfirmed = dozentPraeferenzService.alreadyConfirmed(BEWERBER, DOZENT_MAIL);

    assertThat(alreadyConfirmed).isEqualTo(true);
  }

  @Test
  public void testBooleanAlreadyConfirmed_false(){
    dozentPraeferenzService.addPraeferenz(addDozentPraeferenzDTO);
    boolean alreadyConfirmed = dozentPraeferenzService.alreadyConfirmed("andererBewerber", DOZENT_MAIL);

    assertThat(alreadyConfirmed).isEqualTo(false);
  }
}
