package mops.authentication.account;

import mops.authentication.account.keycloak.Account;

public class AuthenticationManager {
  private Account account;

  private static final String ROLE_ORGA = "ROLE_orga";
  private static final String ROLE_STUDENT = "ROLE_studentin";
  private static final String ROLE_BOSS = "ROLE_verwaltung";
  private static final String ROLE_ADMIN = "ROLE_admin";

  public AuthenticationManager(Account account) {
    this.account = account;
  }

  public boolean isOrganizerButNotStudent() {
    return account.getRoles().contains(ROLE_ORGA) && !account.getRoles().contains(ROLE_STUDENT);
  }

  public boolean isStudentButNotOrganizer() {
    return account.getRoles().contains(ROLE_STUDENT) && !account.getRoles().contains(ROLE_ORGA);
  }

  public boolean isStudent() {
    return account.getRoles().contains(ROLE_STUDENT);
  }

  public boolean isOrganizer() {
    return account.getRoles().contains(ROLE_ORGA);
  }

  public boolean isBoss() {
    return account.getRoles().contains(ROLE_BOSS);
  }

  public boolean isAdmin()  {
    return account.getRoles().contains(ROLE_ADMIN);
  }
}