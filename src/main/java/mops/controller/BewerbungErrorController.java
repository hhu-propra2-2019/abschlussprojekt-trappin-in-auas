package mops.controller;

import mops.Bewerbung1Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
class BewerbungErrorController implements ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(Bewerbung1Application.class);

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  @RequestMapping("/error")
  public String error(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    String originalUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        LOGGER.error("HTTP ERROR 404 for " + originalUri);
        return "error/404";
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        LOGGER.error("HTTP ERROR 500 for " + originalUri);
        return "error/500";
      } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
        LOGGER.error("HTTP ERROR 403 for " + originalUri);
        return "error/403";
      }
    }
    return "redirect:/bewerbung1";
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    req.setAttribute("originalUri", req.getRequestURI());
    req.getRequestDispatcher("/error").forward(req, resp);
  }
}
