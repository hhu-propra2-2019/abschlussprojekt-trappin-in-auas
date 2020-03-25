package mops.domain.services;

import java.util.List;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.ModulAuswahlDTO;
import mops.domain.models.Bewerber;
import mops.domain.models.Dozent;


public interface IBewerberService {
    void addBewerber(Bewerber b);

    BewerberDTO findBewerberByKennung(String kennung);
    List<BewerberDTO> findAlleBewerber();
    List<BewerberDTO> findAlleNichtVerteilteBewerber(List<BewerberDTO> alleBewerber);
    void verteile(String kennung, Dozent dozent);
    List<BewerberDTO> findAlleVerteilteBewerber(List<BewerberDTO> alleBewerber);
    List<Bewerber> findBewerberFuerDozent(String dozentKennung);
    List<Bewerber> findVerteilt();
    List<Bewerber> findNichtVerteilt();

}