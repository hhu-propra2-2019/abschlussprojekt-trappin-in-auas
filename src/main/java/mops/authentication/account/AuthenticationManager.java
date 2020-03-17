package mops.authentication.account;

import mops.authentication.account.keycloak.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:roles.properties")
public class AuthenticationManager {

  @Autowired
  private @Value("${role.orga}") String ROLE_ORGA;
  @Autowired
  private @Value("${role.studentin}") String ROLE_STUDENT;
  @Autowired
  private @Value("${role.boss}") String ROLE_BOSS;
  @Autowired
  private @Value("${role.verteiler}") String ROLE_VERTEILER;

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