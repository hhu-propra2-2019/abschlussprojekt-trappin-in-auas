package mops.domain.services;

import java.util.List;
import mops.domain.database.dto.*;
import mops.domain.models.*;

public interface IModelService {
    Karriere load(KarriereDTO karriereDTO);
    ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO);
    Personalien load(PersonalienDTO personalienDTO);
    Praeferenzen load(PraeferenzenDTO praeferenzenDTO);
    Modul loadModul(ModulDTO modulDTO);
    StudiengangAbschluss load(StudiengangAbschlussDTO fachAbschluss);
    List<Modul> loadModulList(List<ModulDTO> dtoList);
}