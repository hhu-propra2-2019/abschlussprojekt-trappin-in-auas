package mops.bewerbung1.services;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

//ToDo aber erst wenn pdf fertig ist. Unser Ziel ist es die check methode zu überpruefen.
public class PdfServiceTest {

    private BewerberDTO bewerberDTO;

    @BeforeEach
    void setUp() {
        this.bewerberDTO = mock(BewerberDTO.class);

    }
        /* for (PDField field2 : pDAcroForm.getFields()){
                System.out.println(field2.getFullyQualifiedName());
            }*/ //spuckt alle titelfelder aus











}
