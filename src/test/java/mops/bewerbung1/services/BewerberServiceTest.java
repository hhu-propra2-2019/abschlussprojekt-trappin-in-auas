package mops.bewerbung1.services;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import mops.domain.models.Bewerber;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BewerberServiceTest {

    private BewerberService bewerberService;

    @Before
    void setUp() {
        this.bewerberService = mock(BewerberService.class);
    }

    @Test
    void saveBewerberTest(){
        Bewerber b = new Bewerber("jocl100", "Clark", "John", "23.05.1999");
        //ArgumentCaptor<Bewerber> valueCaptor = ArgumentCaptor.forClass(Bewerber.class);

        //when(bewerberService.findBewerberByKennung(Mockito.anyString())).thenReturn(b);
        //doNothing().when(bewerberService).addBewerber(valueCaptor.capture());

        //bewerberService.addBewerber(b);
        //verify(bewerberService).addBewerber(b);
        //assertEquals(bewerberService.findBewerberByKennung("jocl100"), valueCaptor.getValue());
    }

}
