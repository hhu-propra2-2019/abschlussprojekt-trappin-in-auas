package mops.domain.services;

import java.util.List;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.VerteilungDTO;

public interface IVerteilerService {
  List<BewerberDTO> showVerteilteBewerber();//BewerberDTO verteiltan

  List<BewerberDTO> showNichtVerteilteBewerber();//BewerberDTO verteiltan leer siehe repo bewerber

  void addVerteilung(VerteilungDTO verteilungDTO, String unikennung);


}
