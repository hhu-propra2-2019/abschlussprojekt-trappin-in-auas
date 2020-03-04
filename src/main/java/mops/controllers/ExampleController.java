package mops.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mops.models.Bewerber;
import mops.repositories.BewerberRepository;

@Controller
public class ExampleController{

    BewerberRepository bewerberRepository;

    public ExampleController(BewerberRepository bewerberRepository) {
        this.bewerberRepository = bewerberRepository;
    }

    @GetMapping("/example")
    public String alle(){
        Bewerber b1 = new Bewerber("emjo100", "Johnson", "Emma", "23.04.2000");
        Bewerber b2 = new Bewerber("uljo234", "Johanson", "Ulrich", "01.02.1999");
        Bewerber b3 = new Bewerber("mahi932", "Hills", "Mark", "28.02.1997");
        Bewerber b4 = new Bewerber("bewa232", "Watson", "Benjamin", "12.04.1998");
        Bewerber b5 = new Bewerber("gewa101", "George", "Walter", "09.11.1998");
        List<Bewerber> alleB = bewerberRepository.findAll();
        alleB.stream().forEach(x -> System.out.println(x));
        return "example";
    }
}