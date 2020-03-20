package mops.services;

import java.util.List;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.VerteilungDTO;
import mops.domain.services.IVerteilerService;

public class VerteilerService implements IVerteilerService {

  @Override
  public List<BewerberDTO> showVerteilteBewerber() {
    return null;
  }

  @Override
  public List<BewerberDTO> showNichtVerteilteBewerber() {
    return null;
  }

  @Override
  public void addVerteilung(VerteilungDTO verteilungDTO, String unikennung) {

  }
}
