package mops.services;

import org.springframework.stereotype.Service;

import mops.domain.database.dto.BestandeneModuleDTO;
import mops.domain.database.dto.KarriereDTO;
import mops.domain.database.dto.ModulAuswahlDTO;
import mops.domain.database.dto.PersonalienDTO;
import mops.domain.database.dto.PraeferenzenDTO;
import mops.domain.models.BestandeneModule;
import mops.domain.models.Karriere;
import mops.domain.models.ModulAuswahl;
import mops.domain.models.Personalien;
import mops.domain.models.Praeferenzen;
import mops.domain.services.IMappingService;

@Service
public class MappingService implements IMappingService {

    @Override
    public BestandeneModule load(BestandeneModuleDTO bestandeneModuleDTO) {
        return null;
    }

    @Override
    public Karriere load(KarriereDTO karriereDTO) {
        return null;
    }

    @Override
    public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO) {
        return null;
    }

    @Override
    public Personalien load(PersonalienDTO personalienDTO) {
        return null;
    }

    @Override
    public Praeferenzen load(PraeferenzenDTO praeferenzenDTO) {
        return null;
    }
    
}