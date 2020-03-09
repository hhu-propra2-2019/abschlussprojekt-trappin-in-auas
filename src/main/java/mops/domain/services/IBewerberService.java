package mops.domain.services;

import mops.domain.models.fragebogen.Bewerber;

public interface IBewerberService {
    void addBewerber(Bewerber b);

    Bewerber findBewerberByKennung(String kennung);
}