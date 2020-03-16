package mops.domain.services;

import mops.domain.database.dto.*;
import mops.domain.models.*;

public interface IDTOService {
    KarriereDTO load(Karriere karriere);
    PersonalienDTO load(Personalien personalien);
    PraeferenzenDTO load(Praeferenzen praeferenzen);
    BewerberDTO load(Bewerber bewerber);
}
