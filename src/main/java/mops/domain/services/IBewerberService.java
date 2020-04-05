package mops.domain.services;

import java.util.List;
import mops.domain.models.Bewerber;
import mops.domain.models.Dozent;


public interface IBewerberService {
  void addBewerber(Bewerber b);

  Bewerber findBewerberByKennung(String kennung);

  List<Bewerber> findAlleBewerber();

  List<Bewerber> findAlleNichtVerteilteBewerber();

  void verteile(String kennung, Dozent dozent);

  List<Bewerber> findAlleVerteilteBewerber();

  List<Bewerber> findBewerberFuerDozent(String dozentKennung);

  boolean bewerbungExists(String kennung);
}