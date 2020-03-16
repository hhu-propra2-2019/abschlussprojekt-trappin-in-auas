package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.BewerberDTO;


public interface IBewerberService {
    void addBewerber(BewerberDTO b);

    BewerberDTO findBewerberByKennung(String kennung);
    List<BewerberDTO> findAlleBewerber();
    List<BewerberDTO> findAlleNichtVerteilteBewerber(List<BewerberDTO> alleBewerber);
    void verteile(String kennung, String dozent);
}