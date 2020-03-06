package mops.domain.services;

import java.util.List;

import mops.domain.models.Bewerber;

public interface IBewerberService {
    void addBewerber(Bewerber b);

    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAlleBewerber();
    List<Bewerber> findAlleNichtVerteilteBewerber();
}