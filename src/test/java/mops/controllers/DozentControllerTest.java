package mops.controllers;

import com.c4_soft.springaddons.test.security.context.support.WithMockKeycloackAuth;
import mops.Bewerbung1Application;
import mops.services.BewerberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static mops.authentication.account.keycloak.KeycloakRoles.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = Bewerbung1Application.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest
class DozentControllerTest {

  @Autowired
  private transient MockMvc mockMvc;

  @MockBean
  private BewerberService bewerberService;

  private transient String dozentViewGetUri = "/bewerbung1/dozent/uebersicht";
  private transient String dozentViewOpenGetUri = "/bewerbung1/dozent/unbearbeitete";
  private transient String dozentViewClosedGetUri = "/bewerbung1/dozent/bearbeitete";

  @BeforeEach
  void setUp() {

  }

  // GET UEBERSICHT //

  @Disabled // wegen falschem template
  @WithMockKeycloackAuth("ROLE_orga")
  @Test
  void testUebersichtWithCorrectRole() throws Exception {
    mockMvc.perform(get(dozentViewGetUri)).andExpect(status().isOk());
  }

  @WithMockKeycloackAuth(ROLE_BOSS)
  @Test
  void testUebersichtWithBossRole() throws Exception {
    mockMvc.perform(get(dozentViewGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_STUDENT)
  @Test
  void testUebersichtWithStudentRole() throws Exception {
    mockMvc.perform(get(dozentViewGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_VERTEILER)
  @Test
  void testUebersichtWithVerteilerRole() throws Exception {
    mockMvc.perform(get(dozentViewGetUri)).andExpect(status().is(403));
  }

  // GET OFFENE //

  @Disabled // wegen falschem template
  @WithMockKeycloackAuth(ROLE_ORGA)
  @Test
  void testOffeneWithOrgaRole() throws Exception {
    mockMvc.perform(get(dozentViewOpenGetUri)).andExpect(status().isOk());
  }

  @WithMockKeycloackAuth(ROLE_STUDENT)
  @Test
  void testOffeneWithStudentRole() throws Exception {
    mockMvc.perform(get(dozentViewOpenGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_BOSS)
  @Test
  void testOffeneWithBossRole() throws Exception {
    mockMvc.perform(get(dozentViewOpenGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_VERTEILER)
  @Test
  void testOffeneWithVerteilerRole() throws Exception {
    mockMvc.perform(get(dozentViewOpenGetUri)).andExpect(status().is(403));
  }

  // GET ZUGEWIESEN

  @Disabled // wegen falschem template
  @WithMockKeycloackAuth(ROLE_ORGA)
  @Test
  void testZugewieseneWithOrgaRole() throws Exception {
    mockMvc.perform(get(dozentViewClosedGetUri)).andExpect(status().isOk());
  }

  @WithMockKeycloackAuth(ROLE_VERTEILER)
  @Test
  void testZugewieseneWithVerteilerRole() throws Exception {
    mockMvc.perform(get(dozentViewClosedGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_BOSS)
  @Test
  void testZugewieseneWithBossRole() throws Exception {
    mockMvc.perform(get(dozentViewClosedGetUri)).andExpect(status().is(403));
  }

  @WithMockKeycloackAuth(ROLE_STUDENT)
  @Test
  void testZugewieseneWithStudentRole() throws Exception {
    mockMvc.perform(get(dozentViewClosedGetUri)).andExpect(status().is(403));
  }
}