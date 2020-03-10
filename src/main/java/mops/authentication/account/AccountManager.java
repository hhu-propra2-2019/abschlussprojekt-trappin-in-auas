package mops.authentication.account;

import java.util.Set;
import mops.authentication.account.keycloak.Account;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class AccountManager {
  public Account parseFrom(KeycloakAuthenticationToken token) {
    String name = token.getName();

    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    String email = principal.getKeycloakSecurityContext().getIdToken().getEmail();

    String image = principal.getKeycloakSecurityContext().getIdToken().getPicture();

    Set<String> roles = token.getAccount().getRoles();

    return new Account(name, email, image, roles);
  }
}
