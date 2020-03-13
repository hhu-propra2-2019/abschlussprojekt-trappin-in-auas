package mops.controllers;

import java.util.*;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ExampleController {

  private transient BewerberRepository bewerberRepository;

  public ExampleController(BewerberRepository bewerberRepository) {
    this.bewerberRepository = bewerberRepository;
  }

    @GetMapping("/example")
    public String alle(){
	List<BewerberDTO> alleB = bewerberRepository.findAll();
	System.out.println("printing bewerber");
	System.out.println(alleB);
        return "redirect:/";
    }
}
