package mops.controllers;

import static org.mockito.Mockito.when;

import com.c4_soft.springaddons.test.security.context.support.WithMockKeycloackAuth;
import mops.Bewerbung1Application;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.services.BewerberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = Bewerbung1Application.class)
@SpringBootTest
class DozentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BewerberService bewerberService;

  private BewerberDTO bewerberDTO;

  @BeforeEach
  void setUp() {
    bewerberDTO = new BewerberDTO(
            new PersonalienDTO(new AdresseDTO("40489", "Düsseldorf", "Kalkumer Schlossallee", "28"),
                    "abc1234", "Name", "Vorname", new Date(1234L), 25,"Düsseldorf", "deutsch"),
            new KarriereDTO("Arbeitserfahrung", new ImmartikulationsStatusDTO(true, "Informatik"),
                    new StudiengangAbschlussDTO("Informatik", "Bachelor")),
            new PraeferenzenDTO(1, 5, Arrays.asList(new ModulAuswahlDTO(new ModulDTO("RDB", "rdb@cs.hhu.de", "Conrad"), 2)),
                    "Kommentar", EinstiegTyp.NEUEINSTIEG, "Einschränkungen", new BerufModulDTO(Beruf.Korrektor,
                    new ModulDTO("RDB", "rdb@cs.hhu.de", "Conrad")), TutorenSchulungTeilnahme.TEILNAHME),
            "verteilt an");

    when(bewerberService.findAlleBewerber()).thenReturn(Collections.singletonList(bewerberDTO));
    when(bewerberService.findBewerberByKennung("abc1234")).thenReturn(bewerberDTO);
    when(bewerberService.findNichtVerteilt()).thenReturn(Collections.singletonList(bewerberDTO));
    when(bewerberService.findVerteilt()).thenReturn(Collections.emptyList());
  }

  @WithMockKeycloackAuth("orga")
  @Test
  void testUebersichtWithAuth() {


  }
}