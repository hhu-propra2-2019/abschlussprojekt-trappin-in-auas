package mops.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class KeycloakRoleService {

  private transient Map<String, String> roleRedirect;

  public KeycloakRoleService(){
    roleRedirect = new HashMap<>();
    roleRedirect.put("studentin", "/bewerber");
    roleRedirect.put("verteiler", "/verteiler");
    roleRedirect.put("setup", "/setup");
    roleRedirect.put("orga", "/dozent");
  }

  public String getRedirect(String role){
    return roleRedirect.get(role);
  }

  public String getHighestPrivilegeRedirect(Set<String> tokenRoles) {
    return "unimplemented";
  }
}