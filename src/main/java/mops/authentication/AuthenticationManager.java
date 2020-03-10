package mops.authentication;

public class AuthenticationManager {
  private Account account;

  private String ROLE_ORGA = "ROLE_orga";
  private String ROLE_STUDENT = "ROLE_studentin";
  private String ROLE_BOSS = "ROLE_";

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