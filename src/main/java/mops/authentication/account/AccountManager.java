package mops.authentication.account;

import java.util.Set;
import mops.authentication.account.keycloak.Account;
import org.keycloak.KeycloakPrincipal;

public class AccountManager {
  /**
   * Uebersetzt ein Keycloak Principal Objekt in einen Account.
   * @param principal Objekt aus Keycloak Token
   * @param roles Rollen aus Keycloak Token
   * @return Account mit Daten und Rollen
   */
  public Account parseFrom(KeycloakPrincipal principal, Set<String> roles) {
    String name = principal.getKeycloakSecurityContext().getIdToken().getFamilyName();
    name += ", " + principal.getKeycloakSecurityContext().getIdToken().getName();
    String email = principal.getKeycloakSecurityContext().getIdToken().getEmail();
    String image = principal.getKeycloakSecurityContext().getIdToken().getPicture();
    return new Account(name, email, image, roles);
  }
}
