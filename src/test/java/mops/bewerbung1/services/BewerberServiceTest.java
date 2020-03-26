package mops.bewerbung1.services;

import mops.bewerbung1.testutils.ModelGenerator;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.models.Beruf;
import mops.domain.repositories.BewerberRepository;
import mops.domain.repositories.ModulRepository;
import mops.services.BewerberService;
import mops.services.DTOService;
import mops.services.ModelService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class BewerberServiceTest {

  @Mock
  private transient ModulRepository modulRepository;

  @Mock
  private transient BewerberRepository bewerberRepository;

  private transient BewerberService bewerberService;
  private transient DTOService dtoService;
  private transient ModelService modelService;
  private transient ModelGenerator modelGenerator;

  @BeforeEach
  void setUp() {
    this.modelService = new ModelService();
    this.dtoService = new DTOService(modulRepository);
    this.bewerberService = new BewerberService(bewerberRepository, dtoService, modelService);
    this.modelGenerator = new ModelGenerator();
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private void addBewerberDTOMock(List<BewerberDTO> pseudoDatenbank){
    doAnswer(invocation -> {
      BewerberDTO bewerberDTO = (BewerberDTO) invocation.getArguments()[0];
      if(pseudoDatenbank.stream().anyMatch(x -> x.getKennung().equals(bewerberDTO.getKennung()))){
        BewerberDTO gefundene = pseudoDatenbank.stream().filter(x -> x.getKennung().equals(bewerberDTO.getKennung())).findFirst().get();
        pseudoDatenbank.remove(gefundene);
      }
      pseudoDatenbank.add(bewerberDTO);
      return null;
    }).when(bewerberRepository).save(any(BewerberDTO.class));
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private void removeBewerberDTOMock(List<BewerberDTO> pseudoDatenbank) {
    doAnswer(invocation -> {
      pseudoDatenbank.remove((BewerberDTO) invocation.getArguments()[0]);
      return null;
    }).when(bewerberRepository).delete(any(BewerberDTO.class));
  }

  @Test
  public void findAlleBewerberTest() {
    List<BewerberDTO> alleBewerber = new LinkedList<>();
    
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();

    Bewerber bewerber = modelGenerator.generateBewerber();
    bewerber.getPraeferenzen().setModulAuswahl(modulAuswahlList1);
    bewerber.getPersonalien().setVorname("John");

    addBewerberDTOMock(alleBewerber);
    when(bewerberRepository.findAll()).thenReturn(alleBewerber);
    
    bewerberService.addBewerber(bewerber);
    List<Bewerber> bewerberList = bewerberService.findAlleBewerber();

    assertNotNull(bewerberList);
    assertEquals(1, bewerberList.size());
  }

  @Test
  public void findAlle10BewerberTest() {
    List<BewerberDTO> alleBewerber = new LinkedList<>();
 
    addBewerberDTOMock(alleBewerber);
    when(bewerberRepository.findAll()).thenReturn(alleBewerber);
    
    for(int i = 0; i < 10; i++){
      Bewerber b = modelGenerator.generateBewerber();
      b.setKennung("unique"+i);
      bewerberService.addBewerber(b);
    }

    List<Bewerber> bewerberList = bewerberService.findAlleBewerber();

    assertNotNull(bewerberList);
    assertEquals(10, bewerberList.size());
  }

  @Test
  public void removeBewerberTest() {
    List<BewerberDTO> alleBewerber = new LinkedList<>();
    
    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();

    Bewerber bewerber = modelGenerator.generateBewerber();
    bewerber.getPraeferenzen().setModulAuswahl(modulAuswahlList1);

    addBewerberDTOMock(alleBewerber);
    when(bewerberRepository.findAll()).thenReturn(alleBewerber);
    removeBewerberDTOMock(alleBewerber);

    bewerberService.addBewerber(bewerber);

    assertEquals(1, alleBewerber.size());

    bewerberService.removeBewerber(bewerber);

    assertEquals(0, alleBewerber.size());
  }

  @Test
  public void findNichtVerteilteBewerberTest() {
    List<BewerberDTO> alleBewerbungen = new LinkedList<>();

    Modul modul1 = modelGenerator.generateModul();
    Modul modul2 = modelGenerator.generateModul();

    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(new ModulAuswahl(modul1, 2, 2.3, Beruf.Korrektor));
    modulAuswahlList1.add(new ModulAuswahl(modul1, 4, 1.7, Beruf.Korrektor));

    Bewerber bewerber1 = modelGenerator.generateBewerber();
    bewerber1.getPraeferenzen().setModulAuswahl(modulAuswahlList1);

    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(new ModulAuswahl(modul1, 1, 2.7, Beruf.Korrektor));
    modulAuswahlList2.add(new ModulAuswahl(modul2, 3, 3.0, Beruf.Korrektor));

    Bewerber bewerber2 = modelGenerator.generateBewerber();
    bewerber2.getPraeferenzen().setModulAuswahl(modulAuswahlList1);
    bewerber2.setKennung("spezialkennung");

    addBewerberDTOMock(alleBewerbungen);
    when(bewerberRepository.findAll()).thenReturn(alleBewerbungen);
    doAnswer(i -> alleBewerbungen.stream().filter(x -> x.getKennung().equals(i.getArguments()[0])).findFirst().get())
        .when(bewerberRepository).findBewerberByKennung(any(String.class));

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);

    bewerberService.verteile(bewerber1.getKennung(),
        bewerber1.getPraeferenzen().getModulAuswahl().get(0).getModul().getDozent());

    List<Bewerber> bewerberList = bewerberService.findAlleBewerber();
    List<Bewerber> nichtVerteilteBewerber = bewerberService.findAlleNichtVerteilteBewerber();

    assertEquals(2, bewerberList.size());
    assertEquals(1, nichtVerteilteBewerber.size());
  }

  @Test
  public void findAlleVerteilteBewerberTest() {
    List<BewerberDTO> alleBewerbungen = new LinkedList<>();

    Modul modul1 = modelGenerator.generateModul();
    Modul modul2 = modelGenerator.generateModul();

    List<ModulAuswahl> modulAuswahlList1 = new LinkedList<ModulAuswahl>();
    modulAuswahlList1.add(new ModulAuswahl(modul1, 2, 2.3, Beruf.Korrektor));
    modulAuswahlList1.add(new ModulAuswahl(modul1, 4, 1.7, Beruf.Korrektor));

    Bewerber bewerber1 = modelGenerator.generateBewerber();
    bewerber1.getPraeferenzen().setModulAuswahl(modulAuswahlList1);

    List<ModulAuswahl> modulAuswahlList2 = new LinkedList<ModulAuswahl>();
    modulAuswahlList2.add(new ModulAuswahl(modul1, 1, 2.7, Beruf.Korrektor));
    modulAuswahlList2.add(new ModulAuswahl(modul2, 3, 3.0, Beruf.Korrektor));

    Bewerber bewerber2 = modelGenerator.generateBewerber();
    bewerber2.getPraeferenzen().setModulAuswahl(modulAuswahlList1);
    bewerber2.setKennung("spezialkennung");

    addBewerberDTOMock(alleBewerbungen);
    when(bewerberRepository.findAll()).thenReturn(alleBewerbungen);
    doAnswer(i -> alleBewerbungen.stream().filter(x -> x.getKennung().equals(i.getArguments()[0])).findFirst().get())
            .when(bewerberRepository).findBewerberByKennung(any(String.class));

    bewerberService.addBewerber(bewerber1);
    bewerberService.addBewerber(bewerber2);

    bewerberService.verteile(bewerber1.getKennung(),
            bewerber1.getPraeferenzen().getModulAuswahl().get(0).getModul().getDozent());

    List<Bewerber> bewerberList = bewerberService.findAlleBewerber();
    List<Bewerber> verteilteBewerber = bewerberService.findAlleVerteilteBewerber();

    assertEquals(2, bewerberList.size());
    assertEquals(1, verteilteBewerber.size());
  }
}
