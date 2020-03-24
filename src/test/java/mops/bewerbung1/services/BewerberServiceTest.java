package mops.bewerbung1.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.models.Beruf;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;
import mops.services.BewerberService;
import mops.services.DTOService;
import mops.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class BewerberServiceTest {

  private transient IBewerberService bewerberService;
  private transient BewerberRepository bewerberRepository;
  private transient DTOService dtoService;
  private transient ModelService modelService;

  @BeforeEach
  void setUp() {
    this.bewerberRepository = mock(BewerberRepository.class);
    this.dtoService = new DTOService();
    this.modelService = new ModelService();
   this.bewerberService = new BewerberService(bewerberRepository, dtoService, modelService);
  }

}
