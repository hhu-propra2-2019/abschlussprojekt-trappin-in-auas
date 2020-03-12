package mops.bewerbung1.services;



import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;

@SpringBootTest
public class BewerberServiceTest {

    private BewerberService bewerberService;
    private BewerberRepository bewerberRepository;

    @Before
    void setUp() {
        this.bewerberService = mock(BewerberService.class);
        
    }

    @Test
    void hinzufuegenVonBewerbern(){

    }

    private String[] vornamen = {"Luca", "Kristine", "Rosemarie", "Melanie", "Maximilian"};
    private String[] nachnamen = {"Tronke", "Ostermann", "Teubner", "Thoma", "Förstner"};
    private String[] geburtstage = {"27.03.1998", "14.07.2000", "03.01.1999", "22.08.1999", "01.05.1997"};
    private String[] verteilungen = {"Jens", "Golov", "Tratusch"};

    private BewerberModel generateVerteilterBewerber(){
        String vname = fromRandomPosition(vornamen);
        String nname = fromRandomPosition(nachnamen);
        String kennung = vname.substring(0, 3) + nname.substring(0, 3) + (int) Math.random()*999;
        return new BewerberModel(kennung, nname, vname, fromRandomPosition(geburtstage), fromRandomPosition(verteilungen));
    }

    private BewerberModel generateNichtVerteilterBewerber(){
        String vname = fromRandomPosition(vornamen);
        String nname = fromRandomPosition(nachnamen);
        String kennung = vname.substring(0, 3) + nname.substring(0, 3) + (int) Math.random()*999;
        return new BewerberModel(kennung, nname, vname, fromRandomPosition(geburtstage));
    }

    private String fromRandomPosition(String[] array){
        return array[(int) Math.random()*array.length];
    }
}
