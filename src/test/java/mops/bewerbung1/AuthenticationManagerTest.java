package mops.bewerbung1;

import static org.assertj.core.api.Assertions.assertThat;
import static mops.authentication.account.keycloak.KeycloakRoles.*;

import mops.authentication.account.AuthenticationManager;
import mops.authentication.account.keycloak.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationManagerTest {

  @Autowired
  transient AuthenticationManager authenticationManager;

  transient Account orga;
  transient Account student;
  transient Account boss;
  transient Account nobody;
  transient Account verteiler;

  @BeforeAll
  void setUp() {
    orga = new Account("Orgaknilch", "orga@mops", "image", Sets.newSet(ROLE_ORGA));
    student = new Account("Student", "student@mops", "image2", Sets.newSet(ROLE_STUDENT));
    boss = new Account("BigBoss", "boss@mops", "boss_image", Sets.newSet(ROLE_BOSS));
    nobody = new Account("Herr Niemand", "", "null", Sets.newSet());
    verteiler = new Account("ADMIN", "admin@mops", "admimage", Sets.newSet(ROLE_VERTEILER));
  }

  // ORGA //
  @Test
  void testOrgaIsOrga() {
    assertThat(authenticationManager.isOrganizer(orga)).isTrue();
  }

  @Test
  void testOrgaIsNotStudent() {
    assertThat(authenticationManager.isStudent(orga)).isFalse();
  }

  @Test
  void testOrgaisNotAdmin() {
    assertThat(authenticationManager.isAdmin(orga)).isFalse();
  }

  @Test
  void testOrgaIsNotBoss() {
    assertThat(authenticationManager.isBoss(orga)).isFalse();
  }

  // ADMIN //
  @Test
  void testAdminIsAdmin() {
    assertThat(authenticationManager.isAdmin(verteiler)).isTrue();
  }

  @Test
  void testAdminIsNotOrga() {
    assertThat(authenticationManager.isOrganizer(verteiler)).isFalse();
  }

  @Test
  void testAdminIsNotBoss() {
    assertThat(authenticationManager.isBoss(verteiler)).isFalse();
  }

  @Test
  void testAdminIsNotStudent() {
    assertThat(authenticationManager.isStudent(verteiler)).isFalse();
  }

  // STUDENT //
  @Test
  void testStudentIsStudent() {
    assertThat(authenticationManager.isStudent(student)).isTrue();
  }

  @Test
  void testStudentIsNotOrga() {
    assertThat(authenticationManager.isOrganizer(student)).isFalse();
  }

  @Test
  void testStudentIsNotAdmin() {
    assertThat(authenticationManager.isAdmin(student)).isFalse();
  }

  @Test
  void testStudentIsNotBoss() {
    assertThat(authenticationManager.isBoss(student)).isFalse();
  }

  // BOSS //
  @Test
  void testBossIsBoss() {
    assertThat(authenticationManager.isBoss(boss)).isTrue();
  }

  @Test
  void testBossIsNotStudent() {
    assertThat(authenticationManager.isStudent(boss)).isFalse();
  }

  @Test
  void testBossIsNotAdmin() {
    assertThat(authenticationManager.isAdmin(boss)).isFalse();
  }

  @Test
  void testBossIsNotOrga() {
    assertThat(authenticationManager.isOrganizer(boss)).isFalse();
  }

  // NOBODY //
  @Test
  void testNobodyIsNotStudent() {
    assertThat(authenticationManager.isStudent(nobody)).isFalse();
  }

  @Test
  void testNobodyIsNotAdmin() {
    assertThat(authenticationManager.isAdmin(nobody)).isFalse();
  }

  @Test
  void testNobodyIsNotOrga() {
    assertThat(authenticationManager.isOrganizer(nobody)).isFalse();
  }

  @Test
  void testNobodyIsNotBoss() {
    assertThat(authenticationManager.isBoss(nobody)).isFalse();
  }
}
