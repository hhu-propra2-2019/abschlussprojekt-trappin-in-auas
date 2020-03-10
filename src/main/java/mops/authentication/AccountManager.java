package mops.authentication;

import java.util.Set;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class AccountManager {
  Account parseFrom(KeycloakAuthenticationToken token) {
    String name = token.getName();

    KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
    String email = principal.getKeycloakSecurityContext().getIdToken().getEmail();

    String image = principal.getKeycloakSecurityContext().getIdToken().getPicture();

    Set<String> roles = token.getAccount().getRoles();

    return new Account(name, email, image, roles);
  }
}
