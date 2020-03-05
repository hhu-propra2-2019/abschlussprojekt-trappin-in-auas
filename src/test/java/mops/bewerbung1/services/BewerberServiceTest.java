package mops.bewerbung1.services;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import mops.domain.models.Bewerber;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BewerberServiceTest {

    private BewerberService bewerberService;

    @Before
    void setUp() {
        bewerberService = mock(BewerberService.class);
    }

    @Test
    void saveBewerberTest(){
        Bewerber b = new Bewerber("jocl100", "Clark", "John", "23.05.1999");

        when(bewerberService.findBewerberByKennung("jocl100")).thenReturn(b);
        ArgumentCaptor<Bewerber> valueCaptor = ArgumentCaptor.forClass(Bewerber.class);
        doNothing().when(bewerberService).addBewerber(valueCaptor.capture());
        bewerberService.addBewerber(b);

        assertEquals(bewerberService.findBewerberByKennung("jocl100"), valueCaptor.getValue());
    }

}
