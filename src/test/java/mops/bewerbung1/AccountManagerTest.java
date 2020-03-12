package mops.bewerbung1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Set;
import mops.authentication.account.AccountManager;
import mops.authentication.account.keycloak.Account;
import org.junit.jupiter.api.Test;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountManagerTest {

  KeycloakPrincipal principal;
  KeycloakSecurityContext securityContext;
  IDToken idToken;

  @Test
  void testAccountCreation() {
    principal = Mockito.mock(KeycloakPrincipal.class);
    securityContext = Mockito.mock(KeycloakSecurityContext.class);
    idToken = Mockito.mock(IDToken.class);

    when(idToken.getName()).thenReturn("Vorname");
    when(idToken.getFamilyName()).thenReturn("Nachname");
    when(idToken.getEmail()).thenReturn("vorname.nachname@abc.org");
    when(idToken.getPicture()).thenReturn("PICTURE");
    when(securityContext.getIdToken()).thenReturn(idToken);
    when(principal.getKeycloakSecurityContext()).thenReturn(securityContext);

    AccountManager manager = new AccountManager();
    Set<String> roles = Sets.newSet("ROLE_orga");
    Account account1 = new Account("Nachname, Vorname", "vorname.nachname@abc.org", "PICTURE", roles);
    Account account2 = manager.parseFrom(principal, roles);

    assertThat(account2).isEqualTo(account1);
  }

  @Test
  void testDifferentAccounts() {
    principal = Mockito.mock(KeycloakPrincipal.class);
    securityContext = Mockito.mock(KeycloakSecurityContext.class);
    idToken = Mockito.mock(IDToken.class);

    when(idToken.getName()).thenReturn("Vorname");
    when(idToken.getFamilyName()).thenReturn("Nachname");
    when(idToken.getEmail()).thenReturn("vorname.nachname@abc.org");
    when(idToken.getPicture()).thenReturn("PICTURE");
    when(securityContext.getIdToken()).thenReturn(idToken);
    when(principal.getKeycloakSecurityContext()).thenReturn(securityContext);

    AccountManager manager = new AccountManager();
    Account diffAccount = new Account("Fritz Fischer", "ff@gmail.com", "anderesFoto", Sets.newSet());
    Account account = manager.parseFrom(principal, Sets.newSet());

    assertThat(account).isNotEqualTo(diffAccount);
  }
}