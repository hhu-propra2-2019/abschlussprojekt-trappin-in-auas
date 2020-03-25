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
    roleRedirect.put("verteiler", "/verteiler/uebersicht");
    roleRedirect.put("setup", "/setup");
    roleRedirect.put("orga", "/dozent/uebersicht");
  }

  public String getRedirect(String role){
    return roleRedirect.get(role);
  }

  public String getHighestPrivilegeRedirect(Set<String> tokenRoles) {
    if(tokenRoles.contains("setup")){
      return getRedirect("setup");
    }
    if(tokenRoles.contains("verteiler")){
      return getRedirect("verteiler");
    }
    if(tokenRoles.contains("orga")){
      return getRedirect("orga");
    }
    if(tokenRoles.contains("studentin")){
      return getRedirect("studentin");
    }
    return "";
  }
}