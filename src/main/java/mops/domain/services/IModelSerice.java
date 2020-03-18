package mops.domain.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;

import java.util.List;

public interface IModelSerice {
    Karriere load(KarriereDTO karriereDTO);
    ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO);
    Personalien load(PersonalienDTO personalienDTO);
    Praeferenzen load(PraeferenzenDTO praeferenzenDTO);
    Modul loadModul(ModulDTO modulDTO);
    StudiengangAbschluss load(StudiengangAbschlussDTO fachAbschluss);
    List<Modul> loadModulList(List<ModulDTO> dtoList);
}