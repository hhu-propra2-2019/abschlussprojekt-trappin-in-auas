package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.ModulAuswahlDTO;
import mops.domain.models.Bewerber;
import mops.domain.models.Dozent;

import org.springframework.stereotype.Service;

import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;

@Service
public class BewerberService implements IBewerberService {

  private transient BewerberRepository bewerberRepository;
  private transient DTOService mappingService;
  private transient ModelService modelService;

  public BewerberService(BewerberRepository bewerberRepository, DTOService mappingService, ModelService modelService) {
    this.bewerberRepository = bewerberRepository;
    this.mappingService = mappingService;
    this.modelService = modelService;
  }

  @Override
  public void addBewerber(Bewerber b) {
    BewerberDTO bewerberDTO = mappingService.load(b);
    System.out.println("bewerberDTO erstellt:");
    System.out.println(bewerberDTO);
    bewerberRepository.save(bewerberDTO);
  }

  @Override
  public BewerberDTO findBewerberByKennung(String kennung) {
    return bewerberRepository.findBewerberByKennung(kennung);
  }

  public Bewerber findBewerberModelByKennung(String kennung) {
    return modelService.load(bewerberRepository.findBewerberByKennung(kennung));
  }

  public List<BewerberDTO> findAlleBewerber() {
    return bewerberRepository.findAll();
  }

  @Override
  public List<BewerberDTO> findAlleNichtVerteilteBewerber(List<BewerberDTO> alleBewerber) {
    return alleBewerber.stream().filter(x -> x.getVerteiltAn() == null).collect(Collectors.toList());
  }

  public void verteile(String kennung, Dozent dozent) {
    BewerberDTO b = findBewerberByKennung(kennung);
    b.getVerteiltAn().add(mappingService.load(dozent));
    bewerberRepository.save(b);
  }

  public List<BewerberDTO> findAlleVerteilteBewerber(List<BewerberDTO> alleBewerber) {
    return alleBewerber.stream().filter(x -> x.getVerteiltAn() != null).collect(Collectors.toList());
  }

  public List<Bewerber> findNichtVerteilt() {
    return modelService.loadBewerberList(  bewerberRepository.findBewerberDTOBByVerteiltAnIsNull() );
  }

  public List<Bewerber> findVerteilt() {
    return modelService.loadBewerberList(  bewerberRepository.findByVerteiltAnIsNotNull()  );
  }

  public List<Bewerber> findBewerberFuerDozent(String dozentKennung) {
    List<BewerberDTO> alleBewerber = findAlleBewerber();
    return alleBewerber.stream()
    .filter(x -> modulAuswahlContainsDozent(x.getPraeferenzen().getModulAuswahl(), dozentKennung))
    .map(x -> modelService.load(x)).collect(Collectors.toList());
  }

  private boolean modulAuswahlContainsDozent(List<ModulAuswahlDTO> auswahlDTO, String dozentKennung){
    return auswahlDTO.stream().anyMatch(x -> x.getModul().getDozentMail().equals(dozentKennung));
  }

  
}