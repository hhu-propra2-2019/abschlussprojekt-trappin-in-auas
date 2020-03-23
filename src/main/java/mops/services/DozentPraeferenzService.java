package mops.services;

import java.util.List;
import mops.domain.database.dto.DozentPraeferenzDTO;
import mops.domain.models.DozentPraeferenz;
import mops.domain.repositories.DozentPraeferenzRepo;
import mops.domain.services.IDozentPraeferenzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DozentPraeferenzService implements IDozentPraeferenzService {

  @Autowired
  private transient DozentPraeferenzRepo dozentPraeferenzRepo;

  @Autowired
  private transient DTOService dtoService;

  public DozentPraeferenzService(DozentPraeferenzRepo dozentPraeferenzRepo) {
    this.dozentPraeferenzRepo = dozentPraeferenzRepo;
  }

  @Override
  public void addPraeferenz(DozentPraeferenzDTO dozentPraeferenzDTO) {
    dozentPraeferenzRepo.save(dozentPraeferenzDTO);
  }

  public void addPraeferenz(DozentPraeferenz dozentPraeferenz) {
    dozentPraeferenzRepo.save(dtoService.load(dozentPraeferenz));
  }

  @Override
  public void deletePraeferenz(String bewerber, String dozentMail) {
    dozentPraeferenzRepo.deleteByBewerberAndDozentMail(bewerber, dozentMail);
  }

  @Override
  public Integer getDozentPraeferenz(String bewerber, String dozentMail) {
    List<DozentPraeferenzDTO> matchingRows = dozentPraeferenzRepo.findByBewerberAndDozentMail(bewerber, dozentMail);

    if(matchingRows.isEmpty()){
      return -1;
    }
    return matchingRows.get(0).getPraeferenz();
  }

  @Override
  public boolean alreadyConfirmed(String bewerber, String dozentMail) {
    if(getDozentPraeferenz(bewerber, dozentMail) == -1){
      return false;
    }
    return true;
  }
}
