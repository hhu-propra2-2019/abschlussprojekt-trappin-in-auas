package mops.bewerbung1.services;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.domain.database.dto.BestandeneModuleDTO;
import mops.domain.models.BestandeneModule;
import mops.domain.services.IMappingService;

@SpringBootTest
public class MappingServiceTest {
    private IMappingService mappingService;


    @Test
    public void BestandeneModuleDTOZuBestandeneModuleModel(){
        BestandeneModuleDTO bestandeneModuleDTO = new BestandeneModuleDTO();
        BestandeneModule bestandeneModule = mappingService.load(bestandeneModuleDTO);

        assertNull(bestandeneModule);
    }

    /**
     * Make sure the mappingservice
     * does not break when passing null
     */
    @Test
    public void BestandenesModulDTOIsNullMappingReturnsNull(){
        BestandeneModuleDTO bestandeneModuleDTO = null;
        BestandeneModule bestandeneModule = mappingService.load(bestandeneModuleDTO);

        assertNull(bestandeneModule);
    }
}