package mops.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mops.domain.models.fragebogen.Bewerber;
import mops.domain.repositories.BewerberRepository;

@Controller
public class ExampleController{

    BewerberRepository bewerberRepository;

    public ExampleController(BewerberRepository bewerberRepository) {
        this.bewerberRepository = bewerberRepository;
    }

    @GetMapping("/example")
    public String alle(){
	List<Bewerber> alleB = bewerberRepository.findAll();
	System.out.println("printing bewerber");
	System.out.println(alleB);
        return "redirect:/";
    }
}
