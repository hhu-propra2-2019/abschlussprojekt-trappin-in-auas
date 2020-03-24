package mops.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class KeycloakRoleService {

  private Map<String, String> roleRedirect;

  public KeycloakRoleService(){
    roleRedirect = new HashMap<>();
    roleRedirect.put("studentin", "/bewerber");
  }

  public String getRedirect(String role){
    return roleRedirect.get(role);
  }
}