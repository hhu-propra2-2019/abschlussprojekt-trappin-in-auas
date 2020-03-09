package mops.bewerbung1.services;



import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.models.Bewerber;
import mops.services.BewerberService;

@SpringBootTest
public class BewerberServiceTest {

    private BewerberService bewerberService;
    
    @Before
    void setUp() {
        this.bewerberService = mock(BewerberService.class);
    }

    @Test
    void unzugewiesene3Bewerber(){
        List<Bewerber> bewerber = new ArrayList<>();
        bewerber.add(generateVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateVerteilterBewerber());
        bewerber.add(generateVerteilterBewerber());
        List<Bewerber> nichtVerteilte = doCallRealMethod().when(bewerberService).findAlleNichtVerteilteBewerber(bewerber);

        assertEquals(3, nichtVerteilte.size());
    }

    @Test
    void unzugewiesene0Bewerber(){
        List<Bewerber> bewerber = new ArrayList<>();
        bewerber.add(generateVerteilterBewerber());
        bewerber.add(generateVerteilterBewerber());
        bewerber.add(generateVerteilterBewerber());
        List<Bewerber> nichtVerteilte = doCallRealMethod().when(bewerberService).findAlleNichtVerteilteBewerber(bewerber);

        assertEquals(0, nichtVerteilte.size());
    }

    @Test
    void unzugewiesene10Bewerber(){
        List<Bewerber> bewerber = new ArrayList<>();
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        bewerber.add(generateNichtVerteilterBewerber());
        List<Bewerber> nichtVerteilte = doCallRealMethod().when(bewerberService).findAlleNichtVerteilteBewerber(bewerber);

        assertEquals(10, nichtVerteilte.size());
    }

    private String[] vornamen = {"Luca", "Kristine", "Rosemarie", "Melanie", "Maximilian"};
    private String[] nachnamen = {"Tronke", "Ostermann", "Teubner", "Thoma", "Förstner"};
    private String[] geburtstage = {"27.03.1998", "14.07.2000", "03.01.1999", "22.08.1999", "01.05.1997"};
    private String[] verteilungen = {"Jens", "Golov", "Tratusch"};

    private Bewerber generateVerteilterBewerber(){
        String vname = fromRandomPosition(vornamen);
        String nname = fromRandomPosition(nachnamen);
        String kennung = vname.substring(0, 3) + nname.substring(0, 3) + (int) Math.random()*999;
        return new Bewerber(kennung, nname, vname, fromRandomPosition(geburtstage), fromRandomPosition(verteilungen));
    }

    private Bewerber generateNichtVerteilterBewerber(){
        String vname = fromRandomPosition(vornamen);
        String nname = fromRandomPosition(nachnamen);
        String kennung = vname.substring(0, 3) + nname.substring(0, 3) + (int) Math.random()*999;
        return new Bewerber(kennung, nname, vname, fromRandomPosition(geburtstage));
    }

    private String fromRandomPosition(String[] array){
        return array[(int) Math.random()*array.length];
    }
}
