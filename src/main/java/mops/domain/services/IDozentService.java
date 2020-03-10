package mops.domain.services;

import java.util.List;
import mops.domain.models.lehrstuhl.Dozent;

public interface IDozentService {

  void add(Dozent dozent);

  List<Dozent> findAll();


}
