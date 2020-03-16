package mops.domain.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;

public interface IMappingService {
    public Karriere load(KarriereDTO karriereDTO);
    public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO);
    public Personalien load(PersonalienDTO personalienDTO);
    public Praeferenzen load(PraeferenzenDTO praeferenzenDTO);
}