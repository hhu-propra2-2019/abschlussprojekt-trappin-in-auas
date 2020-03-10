package mops.authentication.account;

import mops.authentication.account.keycloak.Account;

public class AuthenticationManager {
  private Account account;

  private static final String ROLE_ORGA = "ROLE_orga";
  private static final String ROLE_STUDENT = "ROLE_studentin";
  private static final String ROLE_BOSS = "ROLE_verwaltung";

  public AuthenticationManager(Account account) {
    this.account = account;
  }

  boolean isOrganizerButNotStudent() {
    return account.getRoles().contains(ROLE_ORGA) && !account.getRoles().contains(ROLE_STUDENT);
  }

  boolean isStudentButNotOrganizer() {
    return account.getRoles().contains(ROLE_STUDENT) && !account.getRoles().contains(ROLE_ORGA);
  }

  boolean isStudent() {
    return account.getRoles().contains(ROLE_STUDENT);
  }

  boolean isOrganizer() {
    return account.getRoles().contains(ROLE_ORGA);
  }

  boolean isBoss() {
    return account.getRoles().contains(ROLE_BOSS);
  }
}