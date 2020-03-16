package mops.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.c4_soft.springaddons.test.security.context.support.WithMockKeycloackAuth;
import java.util.ArrayList;
import java.util.List;
import mops.Bewerbung1Application;
import mops.domain.database.dto.ModulDTO;
import mops.services.ModulService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = Bewerbung1Application.class)
@SpringBootTest
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class BossControllerTest {

  @Autowired
  private transient MockMvc mockMvc;

  @MockBean
  private transient ModulService modulService;

  private final static String getModulesURL = "/bewerbung1/boss/modules";
  private final static String postModuleURL = "/bewerbung1/boss/addModul";

// GET REQUESTS //

  @WithMockKeycloackAuth("boss")
  @Test
  void testGetStatusOK() throws Exception {
    ModulDTO modul = new ModulDTO("Rechnerarchitektur", "ra@cs.hhu.de", "Conrad");
    List<ModulDTO> module = new ArrayList<>();
    module.add(modul);

    when(modulService.findAllModule()).thenReturn(module);

    mockMvc.perform(get(getModulesURL)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  @WithMockKeycloackAuth("boss")
  void testGetWithLogin() throws Exception {
    ModulDTO modul = new ModulDTO("Rechnerarchitektur", "ra@cs.hhu.de", "Conrad");
    List<ModulDTO> module = new ArrayList<>();
    module.add(modul);

    when(modulService.findAllModule()).thenReturn(module);

    mockMvc.perform(get(getModulesURL)
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  @WithMockKeycloackAuth("orga")
  void testGetWithOrgaRole() throws Exception {
    mockMvc.perform(get(getModulesURL)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
  }

  @Test
  @WithMockKeycloackAuth("studentin")
  void testWithStudentRole() throws Exception {
    mockMvc.perform(get(getModulesURL)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
  }

  @Test
  @WithMockKeycloackAuth("")
  void testGetWithoutRole() throws Exception {
    mockMvc.perform(get(getModulesURL)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
  }

  @Test
  void testGetWithoutLogin() throws Exception {
    mockMvc.perform(get(getModulesURL)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(302));
  }

// POST REQUEST //
  @Test
  void testPostWithoutLogin() throws Exception {
    mockMvc.perform(post(postModuleURL)
            .param("modulName", "RDB")
            .param("dozentMail", "rdb@cs.hhu.de")
            .param("dozentName", "Conrad")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().is(302));
  }

  @Test
  @WithMockKeycloackAuth("boss")
  void testPostWithLogin() throws Exception {
    mockMvc.perform(post(postModuleURL)
        .param("modulName", "RDB")
            .param("dozentMail", "rdb@cs.hhu.de")
            .param("dozentName", "Conrad")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
  }

}