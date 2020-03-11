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

  AuthenticationManager authenticationManager;

  Account orga;
  Account student;
  Account boss;
  Account nobody;
  Account admin;

  @BeforeAll
  void setUp() {
    orga = new Account("Orgaknilch", "orga@mops", "image", Sets.newSet("ROLE_orga"));
    student = new Account("Student", "student@mops", "image2", Sets.newSet("ROLE_studentin"));
    boss = new Account("BigBoss", "boss@mops", "boss_image", Sets.newSet("ROLE_verwaltung"));
    nobody = new Account("Herr Niemand", "", "null", Sets.newSet());
    admin = new Account("ADMIN", "admin@mops", "admimage", Sets.newSet("ROLE_admin"));
  }

  // ORGA //
  @Test
  void testOrgaIsOrga() {
    authenticationManager = new AuthenticationManager(orga);
    assertThat(authenticationManager.isOrganizer()).isTrue();
  }

  @Test
  void testOrgaIsNotStudent() {
    authenticationManager = new AuthenticationManager(orga);
    assertThat(authenticationManager.isStudent()).isFalse();
  }

  @Test
  void testOrgaisNotAdmin() {
    authenticationManager = new AuthenticationManager(orga);
    assertThat(authenticationManager.isAdmin()).isFalse();
  }

  @Test
  void testOrgaIsNotBoss() {
    authenticationManager = new AuthenticationManager(orga);
    assertThat(authenticationManager.isBoss()).isFalse();
  }

  // ADMIN //
  @Test
  void testAdminIsAdmin() {
    authenticationManager = new AuthenticationManager(admin);
    assertThat(authenticationManager.isAdmin()).isTrue();
  }

  @Test
  void testAdminIsNotOrga() {
    authenticationManager = new AuthenticationManager(admin);
    assertThat(authenticationManager.isOrganizer()).isFalse();
  }

  @Test
  void testAdminIsNotBoss() {
    authenticationManager = new AuthenticationManager(admin);
    assertThat(authenticationManager.isBoss()).isFalse();
  }

  @Test
  void testAdminIsNotStudent() {
    authenticationManager = new AuthenticationManager(admin);
    assertThat(authenticationManager.isStudent()).isFalse();
  }

  // STUDENT //
  @Test
  void testStudentIsStudent() {
    authenticationManager = new AuthenticationManager(student);
    assertThat(authenticationManager.isStudent()).isTrue();
  }

  @Test
  void testStudentIsNotOrga() {
    authenticationManager = new AuthenticationManager(student);
    assertThat(authenticationManager.isOrganizer()).isFalse();
  }

  @Test
  void testStudentIsNotAdmin() {
    authenticationManager = new AuthenticationManager(student);
    assertThat(authenticationManager.isAdmin()).isFalse();
  }

  @Test
  void testStudentIsNotBoss() {
    authenticationManager = new AuthenticationManager(student);
    assertThat(authenticationManager.isBoss()).isFalse();
  }

  // BOSS //
  @Test
  void testBossIsBoss() {
    authenticationManager = new AuthenticationManager(boss);
    assertThat(authenticationManager.isBoss()).isTrue();
  }

  @Test
  void testBossIsNotStudent() {
    authenticationManager = new AuthenticationManager(boss);
    assertThat(authenticationManager.isStudent()).isFalse();
  }

  @Test
  void testBossIsNotAdmin() {
    authenticationManager = new AuthenticationManager(boss);
    assertThat(authenticationManager.isAdmin()).isFalse();
  }

  @Test
  void testBossIsNotOrga() {
    authenticationManager = new AuthenticationManager(boss);
    assertThat(authenticationManager.isOrganizer()).isFalse();
  }

  // NOBODY //
  @Test
  void testNobodyIsNotStudent() {
    authenticationManager = new AuthenticationManager(nobody);
    assertThat(authenticationManager.isStudent()).isFalse();
  }

  @Test
  void testNobodyIsNotAdmin() {
    authenticationManager = new AuthenticationManager(nobody);
    assertThat(authenticationManager.isAdmin()).isFalse();
  }

  @Test
  void testNobodyIsNotOrga() {
    authenticationManager = new AuthenticationManager(nobody);
    assertThat(authenticationManager.isOrganizer()).isFalse();
  }

  @Test
  void testNobodyIsNotBoss() {
    authenticationManager = new AuthenticationManager(nobody);
    assertThat(authenticationManager.isBoss()).isFalse();
  }
}
