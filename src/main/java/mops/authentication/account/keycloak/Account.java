package mops.authentication.account.keycloak;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {

  private final String name;
  private final String email;
  private final String image;
  private final Set<String> roles;
}
