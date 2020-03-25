package mops.bewerbung1.services;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.services.BewerberService;
import mops.services.PDFService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;

//ToDo aber erst wenn pdf fertig ist. Unser Ziel ist es die check methode zu überpruefen.
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

@SpringBootTest

public class PdfServiceTest {

}
