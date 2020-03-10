package mops.bewerbung1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import mops.authentication.account.AccountManager;
import mops.authentication.account.keycloak.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class AccountManagerTest {

  private KeycloakAuthenticationToken tokenMock;
  private KeycloakPrincipal principalMock;
  private IDToken idTokenMock;

  @BeforeAll
  public void setUp() throws Exception {
    tokenMock = Mockito.mock(KeycloakAuthenticationToken.class);
    principalMock = Mockito.mock(KeycloakPrincipal.class);
    idTokenMock = Mockito.mock(IDToken.class);
  }


  /*public void tearDown() throws Exception {
    tokenMock = null;
    principalMock = null;
    idTokenMock = null;
  }*/

  @Test
  public void testAccountCreate() throws Exception {
    Set<String> roles = new HashSet<>();
    roles.add("ROLE_orga");

    when(idTokenMock.getEmail()).thenReturn("abc@example.com");
    when(idTokenMock.getPicture()).thenReturn("PICTURE");

    when(principalMock.getKeycloakSecurityContext().getIdToken()).thenReturn(idTokenMock);

    when(tokenMock.getAccount().getRoles()).thenReturn(roles);
    when(tokenMock.getName()).thenReturn("NAME");
    when(tokenMock.getPrincipal()).thenReturn(principalMock);

    AccountManager manager = new AccountManager();
    Account account = manager.parseFrom(tokenMock);

    Account account2 = new Account("NAME", "abc@example.com", "PICTURE", roles);


    assertThat(account).isEqualTo(account2);
  }

}
