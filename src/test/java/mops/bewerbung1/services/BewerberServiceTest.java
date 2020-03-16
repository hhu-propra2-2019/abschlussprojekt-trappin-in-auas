package mops.bewerbung1.services;

import static org.mockito.Mockito.mock;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BewerberServiceTest {

    transient private BewerberService bewerberService;
    private BewerberRepository bewerberRepository;

    @BeforeEach
    void setUp() {
        this.bewerberService = mock(BewerberService.class);
        
    }

    @Test
    void hinzufuegenVonBewerbern() {

    }

    private transient String[] vornamen = {"Luca", "Kristine", "Rosemarie", "Melanie", "Maximilian"};
    private transient String[] nachnamen = {"Tronke", "Ostermann", "Teubner", "Thoma", "Förstner"};
    private transient String[] geburtstage = {"27.03.1998", "14.07.2000", "03.01.1999", "22.08.1999", "01.05.1997"};
    private transient String[] verteilungen = {"Jens", "Golov", "Tratusch"};

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
