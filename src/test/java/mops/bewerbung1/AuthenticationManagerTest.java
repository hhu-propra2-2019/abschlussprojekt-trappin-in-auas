package mops.bewerbung1;

import static org.assertj.core.api.Assertions.assertThat;

import mops.authentication.account.AuthenticationManager;
import mops.authentication.account.keycloak.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.internal.util.collections.Sets;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationManagerTest {

  Account orga;
  Account student;
  Account boss;
  Account nobody;
  Account admin;

  @BeforeAll
  void setUp() {
    orga = new Account("Orgaknilch", "orga@mops", "image", Sets.newSet("ROLE_orga"));
    student = new Account("Student", "student@mops", "image2", Sets.newSet("ROLE_studentin"));
    boss = new Account("BigBoss", "boss@mops", "boss_image", Sets.newSet("ROLE_boss"));
    nobody = new Account("Herr Niemand", "", "null", Sets.newSet());
    admin = new Account("ADMIN", "admin@mops", "admimage", Sets.newSet("ROLE_admin"));
  }

// ORGA //
  @Test
  void testOrgaIsOrga() {
    AuthenticationManager authManager = new AuthenticationManager(orga);
    assertThat(authManager.isOrganizer()).isTrue();
  }

  @Test
  void testOrgaIsNotStudent() {
    AuthenticationManager authManager = new AuthenticationManager(orga);
    assertThat(authManager.isStudent()).isFalse();
  }

  @Test
  void testOrgaisNotAdmin() {
    AuthenticationManager authManager = new AuthenticationManager(orga);
    assertThat(authManager.isAdmin()).isFalse();
  }

  @Test
  void testOrgaIsNotBoss() {
    AuthenticationManager authManager = new AuthenticationManager(orga);
    assertThat(authManager.isBoss()).isFalse();
  }

// ADMIN //
  @Test
  void testAdminIsAdmin() {
    AuthenticationManager authManager = new AuthenticationManager(admin);
    assertThat(authManager.isAdmin()).isTrue();
  }

  @Test
  void testAdminIsNotOrga() {
    AuthenticationManager authmanager = new AuthenticationManager(admin);
    assertThat(authmanager.isOrganizer()).isFalse();
  }

  // ...
}
