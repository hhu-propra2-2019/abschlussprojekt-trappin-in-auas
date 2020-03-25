package mops.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.DozentPraeferenzDTO;
import mops.domain.models.DozentPraeferenz;
import mops.domain.repositories.BewerberRepository;
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
  private transient ZyklusDirigentService zyklusDirigentService;

  @Autowired
  private transient BewerberRepository bewerberRepository;


  @Override
  public void addPraeferenz(DozentPraeferenzDTO dozentPraeferenzDTO) {
    if (zyklusDirigentService.getDozentenPhase()) {
      try {
        BewerberDTO bewerberDTO = bewerberService
                .findBewerberByKennung(dozentPraeferenzDTO.getBewerber());

        List<DozentPraeferenzDTO> existierndeBewertung = bewerberDTO.getDozentPraeferenz()
                .stream()
                .filter(x -> x.getDozentMail().equals(dozentPraeferenzDTO.getDozentMail()))
                .collect(Collectors.toList());

        if (existierndeBewertung.isEmpty()) {
          bewerberDTO.getDozentPraeferenz().add(dozentPraeferenzDTO);
        } else {
          existierndeBewertung.get(0).setPraeferenz(dozentPraeferenzDTO.getPraeferenz());
        }
        bewerberRepository.save(bewerberDTO);

      } catch (Exception e) {
        return;
      }
    }
  }

  public void addPraeferenz(DozentPraeferenz dozentPraeferenz) {
    if (zyklusDirigentService.getDozentenPhase()) {
      DozentPraeferenzDTO dozentPraeferenzDTO = dtoService.load(dozentPraeferenz);
      addPraeferenz(dozentPraeferenzDTO);
    }
  }

  @Override
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public void deletePraeferenz(String bewerber, String dozentMail) {
    if (zyklusDirigentService.getDozentenPhase()) {
      BewerberDTO bewerberDTO = bewerberService.findBewerberByKennung(bewerber);
      List<DozentPraeferenzDTO> matching = getMatchingDozentPraeferenz(bewerber, dozentMail);

      if (!matching.isEmpty()) {
        bewerberDTO.getDozentPraeferenz().removeAll(matching);
        bewerberRepository.save(bewerberDTO);
      }
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

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private List<DozentPraeferenzDTO> getMatchingDozentPraeferenz(String bewerber, String dozentMail){
    BewerberDTO bewerberDTO = bewerberService.findBewerberByKennung(bewerber);
    List<DozentPraeferenzDTO> matching;
    try {
      matching =
          bewerberDTO.getDozentPraeferenz().stream()
              .filter(d -> d.getBewerber().equals(bewerber) && d.getDozentMail().equals(dozentMail))
              .collect(Collectors.toList());
    } catch (Exception e) {
      matching = Collections.emptyList();
    }


    return matching;

  }

}
