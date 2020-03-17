package mops.domain.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;

public interface IModelService {
    Karriere load(KarriereDTO karriereDTO);
    ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO);
    Personalien load(PersonalienDTO personalienDTO);
    Praeferenzen load(PraeferenzenDTO praeferenzenDTO);
}