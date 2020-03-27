package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import mops.services.KeycloakRoleService;
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class KeycloakRoleServiceTest {

  private static final String ORGA = "orga";
  private static final String STUDENTIN = "studentin";
  private static final String VERTEILER = "verteiler";
  private static final String BOSS = "setup";

  private transient KeycloakRoleService keycloakRoleService;

  @BeforeEach
  public void setUp(){
    keycloakRoleService = new KeycloakRoleService();
  }

  @Test
  public void studentRedirectPfad(){
    assertEquals("/bewerber", keycloakRoleService.getRedirect(STUDENTIN));
  }

  @Test
  public void verteilerRedirectPfad(){
    assertEquals("/verteiler/uebersicht", keycloakRoleService.getRedirect(VERTEILER));
  }

  @Test
  public void setupRedirectPfad(){
    assertEquals("/setup", keycloakRoleService.getRedirect(BOSS));
  }

  @Test
  public void dozentRedirectPfad(){
    assertEquals("/dozent/uebersicht", keycloakRoleService.getRedirect(ORGA));
  }

  @Test
  public void highestPrivilegeAllPrivileges(){
    Set<String> tokenRoles = Stream.of(STUDENTIN, ORGA, VERTEILER, BOSS).collect(Collectors.toSet());

    assertEquals("/setup", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }

  @Test
  public void highestPrivilegeOnlyStudent(){
    Set<String> tokenRoles = Stream.of(STUDENTIN).collect(Collectors.toSet());

    assertEquals("/bewerber", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }

  @Test
  public void highestPrivilegeOrga(){
    Set<String> tokenRoles = Stream.of("student", ORGA).collect(Collectors.toSet());

    assertEquals("/dozent/uebersicht", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }

  @Test
  public void highestPrivilegeVerteiler(){
    Set<String> tokenRoles = Stream.of(STUDENTIN, ORGA, VERTEILER).collect(Collectors.toSet());

    assertEquals("/verteiler/uebersicht", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }

  @Test
  public void highestPrivilegeNoKnownPrivilege(){
    Set<String> tokenRoles = Stream.of("weirdsht").collect(Collectors.toSet());

    assertEquals("", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }
}