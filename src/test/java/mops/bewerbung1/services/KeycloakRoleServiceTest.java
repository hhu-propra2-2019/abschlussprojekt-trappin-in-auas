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

  private transient KeycloakRoleService keycloakRoleService;

  @BeforeEach
  public void setUp(){
    keycloakRoleService = new KeycloakRoleService();
  }

  @Test
  public void studentRedirectPfad(){
    assertEquals("/bewerber", keycloakRoleService.getRedirect("studentin"));
  }

  @Test
  public void verteilerRedirectPfad(){
    assertEquals("/verteiler/uebersicht", keycloakRoleService.getRedirect("verteiler"));
  }

  @Test
  public void setupRedirectPfad(){
    assertEquals("/setup", keycloakRoleService.getRedirect("setup"));
  }

  @Test
  public void dozentRedirectPfad(){
    assertEquals("/dozent/uebersicht", keycloakRoleService.getRedirect("orga"));
  }

  @Test
  public void highestPrivilege(){
    Set<String> tokenRoles = Stream.of("studentin", "orga", "verteiler", "setup").collect(Collectors.toSet());

    assertEquals("/setup", keycloakRoleService.getHighestPrivilegeRedirect(tokenRoles));
  }
}