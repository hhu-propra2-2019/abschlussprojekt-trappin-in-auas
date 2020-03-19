package mops.domain.services;

import java.util.ArrayList;
import java.util.List;
import mops.domain.database.dto.DozentPraeferenzDTO;

public interface IDozentPraeferenzService {
  void addPraeferenz(DozentPraeferenzDTO dozentPraeferenzDTO);

  void deletePraeferenz(String bewerber, String dozentMail);

  Integer getDozentPraeferenz(String bewerber, String dozentMail);




}
