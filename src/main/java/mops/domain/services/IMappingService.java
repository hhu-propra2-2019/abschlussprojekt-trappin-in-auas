package mops.domain.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;

public interface IMappingService {
    public BestandeneModule load(BestandeneModuleDTO bestandeneModuleDTO);
    public Karriere load(KarriereDTO karriereDTO);
    public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO);
    public Personalien load(PersonalienDTO personalienDTO);
    public Praeferenzen load(PraeferenzenDTO praeferenzenDTO);
}