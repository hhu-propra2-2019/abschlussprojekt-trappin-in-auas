package mops.controllers;

import java.util.List;
import mops.domain.database.dto.Bewerber;
import mops.domain.repositories.BewerberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ExampleController {

  BewerberRepository bewerberRepository;

  public ExampleController(BewerberRepository bewerberRepository) {
    this.bewerberRepository = bewerberRepository;
  }

  @GetMapping("/example")
  public String alle() {
    List<Bewerber> alleB = bewerberRepository.findAll();
    System.out.println("printing bewerber");
    System.out.println(alleB);
    return "redirect:/";
  }
}
