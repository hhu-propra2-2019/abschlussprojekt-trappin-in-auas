package mops.authentication.account;

import static mops.authentication.account.keycloak.KeycloakRoles.*;

import mops.authentication.account.keycloak.Account;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {



  public boolean isOrganizerButNotStudent(Account account) {
    return account.getRoles().contains(ROLE_ORGA) && !account.getRoles().contains(ROLE_STUDENT);
  }

  public boolean isStudentButNotOrganizer(Account account) {
    return account.getRoles().contains(ROLE_STUDENT) && !account.getRoles().contains(ROLE_ORGA);
  }

  public boolean isStudent(Account account) {
    return account.getRoles().contains(ROLE_STUDENT);
  }

  public boolean isOrganizer(Account account) {
    return account.getRoles().contains(ROLE_ORGA);
  }

  public boolean isBoss(Account account) {
    return account.getRoles().contains(ROLE_BOSS);
  }

  public boolean isAdmin(Account account)  {
    return account.getRoles().contains(ROLE_VERTEILER);
  }
}