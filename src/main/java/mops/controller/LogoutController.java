package mops.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
  @GetMapping("/logout")
  public String logout(HttpServletRequest request) throws Exception {
    request.logout();
    return "redirect:/bewerbung1";
  }

  @GetMapping("/bewerbung1/logout")
  public String logoutFromSubSystem(HttpServletRequest request) throws Exception {
    request.logout();
    return "redirect:/bewerbung1";
  }
}
