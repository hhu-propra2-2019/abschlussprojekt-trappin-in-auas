package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.Bewerber;


public interface IBewerberService {
    void addBewerber(Bewerber b);

    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAlleBewerber();
    List<Bewerber> findAlleNichtVerteilteBewerber(List<Bewerber> alleBewerber);
    void verteile(String kennung, String dozent);
}