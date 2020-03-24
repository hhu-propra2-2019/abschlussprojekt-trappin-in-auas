package mops.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.DozentPraeferenzDTO;
import mops.domain.models.DozentPraeferenz;
import mops.domain.repositories.BewerberRepository;
import mops.domain.repositories.DozentPraeferenzRepo;
import mops.domain.services.IDozentPraeferenzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DozentPraeferenzService implements IDozentPraeferenzService {

  @Autowired
  private transient BewerberService bewerberService;

  @Autowired
  private transient DTOService dtoService;

  @Autowired
  private transient BewerberRepository bewerberRepository;


  @Override
  public void addPraeferenz(DozentPraeferenzDTO dozentPraeferenzDTO) {
    try {
      BewerberDTO bewerberDTO = bewerberService
          .findBewerberByKennung(dozentPraeferenzDTO.getBewerber());

      bewerberDTO.getDozentPraeferenz().add(dozentPraeferenzDTO);
      bewerberRepository.save(bewerberDTO);
      System.out.println("suche mit"+dozentPraeferenzDTO.getBewerber());

    }
    catch (NullPointerException e){
      return;
    }

  }

  public void addPraeferenz(DozentPraeferenz dozentPraeferenz) {
    DozentPraeferenzDTO dozentPraeferenzDTO = dtoService.load(dozentPraeferenz);
    addPraeferenz(dozentPraeferenzDTO);
  }

  @Override
  public void deletePraeferenz(String bewerber, String dozentMail) {
    BewerberDTO bewerberDTO = bewerberService.findBewerberByKennung(bewerber);
    List<DozentPraeferenzDTO> matching = getMatchingDozentPraeferenz(bewerber, dozentMail);

    if(!matching.isEmpty()){
      bewerberDTO.getDozentPraeferenz().removeAll(matching);
      bewerberRepository.save(bewerberDTO);
    }

  }

  @Override
  public Integer getDozentPraeferenz(String bewerber, String dozentMail) {
    List<DozentPraeferenzDTO> matching = getMatchingDozentPraeferenz(bewerber, dozentMail);

    if(matching.isEmpty()){
      return -1;
    }
    return matching.get(0).getPraeferenz();
  }

  @Override
  public boolean alreadyConfirmed(String bewerber, String dozentMail) {
    if(getDozentPraeferenz(bewerber, dozentMail) == -1){
      return false;
    }
    return true;
  }

  private List<DozentPraeferenzDTO> getMatchingDozentPraeferenz(String bewerber, String dozentMail){
    BewerberDTO bewerberDTO = bewerberService.findBewerberByKennung(bewerber);
    List<DozentPraeferenzDTO> matching;
    try {
      matching =
          bewerberDTO.getDozentPraeferenz().stream()
              .filter(d -> d.getBewerber().equals(bewerber) && d.getDozentMail().equals(dozentMail))
              .collect(Collectors.toList());
    }catch (NullPointerException e){
      matching = Collections.emptyList();
    }


    return matching;

  }

}
