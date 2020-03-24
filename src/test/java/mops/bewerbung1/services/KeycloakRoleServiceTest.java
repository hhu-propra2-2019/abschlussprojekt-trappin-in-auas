package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.services.KeycloakRoleService;

@SpringBootTest
public class KeycloakRoleServiceTest {

  private transient KeycloakRoleService keycloakRoleService;

  @BeforeEach
  public void setup(){
    keycloakRoleService = new KeycloakRoleService();
  }

  @Test
  public void studentRedirectPfad(){
    assertEquals(keycloakRoleService.getRedirect("studentin"), "/bewerber");
  }

  @Test
  public void verteilerRedirectPfad(){
    assertEquals(keycloakRoleService.getRedirect("verteiler"), "/verteiler");
  }

  @Test
  public void setupRedirectPfad(){
    assertEquals(keycloakRoleService.getRedirect("setup"), "/setup");
  }

  @Test
  public void dozentRedirectPfad(){
    assertEquals(keycloakRoleService.getRedirect("orga"), "/dozent");
  }

  @Test
  public void s(){
    Set<String> tokenRoles = Stream.of("studentin").collect(Collectors.toSet());
  }
}