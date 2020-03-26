package mops.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import mops.domain.database.dto.*;
import mops.domain.models.*;

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

  public Bewerber initialiseBewerber() {
    Bewerber b = new Bewerber(new Karriere(), new Personalien(), new Praeferenzen());
    b.getPraeferenzen().setModulAuswahl(new ArrayList<>()); // avoid list beeing null errors
    b.getPraeferenzen().getModulAuswahl().add(new ModulAuswahl());
    return b;
  }

  @Override
  public void addBewerber(Bewerber b) {
    BewerberDTO bewerberDTO = mappingService.load(b);
    bewerberRepository.save(bewerberDTO);
  }

  public void addBewerber(Bewerber b, String kennung) {
    b.setKennung(kennung);
    //because status checkbox is inverted
    b.getKarriere().getImmartikulationsStatus().setStatus(!b.getKarriere().getImmartikulationsStatus().isStatus());
    BewerberDTO bewerberDTO = mappingService.load(b);
    BewerberDTO zuFindenderBewerber = bewerberRepository.findBewerberByKennung(kennung);

    if (zuFindenderBewerber != null) {
      bewerberDTO.setId(zuFindenderBewerber.getId());
    }
    bewerberRepository.save(bewerberDTO);
  }

  @Override
  public BewerberDTO findBewerberByKennung(String kennung) {
    return bewerberRepository.findBewerberByKennung(kennung);
  }

  public List<BewerberDTO> findAlleBewerber() {
    return bewerberRepository.findAll();
  }

  @Override
  public List<BewerberDTO> findAlleNichtVerteilteBewerber(List<BewerberDTO> alleBewerber) {
    return alleBewerber.stream().filter(x -> x.getVerteiltAn() == null || x.getVerteiltAn().size() == 0 ).collect(Collectors.toList());
  }

  public void verteile(String kennung, Dozent dozent) {
    BewerberDTO b = bewerberRepository.findBewerberByKennung(kennung);
    b.getVerteiltAn().add(mappingService.load(dozent));
    bewerberRepository.save(b);
  }

  public List<BewerberDTO> findAlleVerteilteBewerber(List<BewerberDTO> alleBewerber) {
    return alleBewerber.stream().filter(x -> x.getVerteiltAn() != null).collect(Collectors.toList());
  }

  public List<Bewerber> findNichtVerteilt() {
    return modelService.loadBewerberList(bewerberRepository.findBewerberDTOBByVerteiltAnIsNull());
  }

  public List<Bewerber> findVerteilt() {
    return modelService.loadBewerberList(bewerberRepository.findByVerteiltAnIsNotNull());
  }

  public List<Bewerber> findBewerberFuerDozent(String dozentKennung) {
    List<BewerberDTO> alleBewerber = findAlleBewerber();
    return alleBewerber.stream()
        .filter(x -> modulAuswahlContainsDozent(x.getPraeferenzen().getModulAuswahl(), dozentKennung))
        .map(x -> modelService.load(x)).collect(Collectors.toList());
  }

  private boolean modulAuswahlContainsDozent(List<ModulAuswahlDTO> auswahlDTO, String dozentKennung) {
    return auswahlDTO.stream().anyMatch(x -> x.getModul().getDozentMail().equals(dozentKennung));
  }

  public Bewerber findBewerberModelByKennung(String kennung) {
    return modelService.load(bewerberRepository.findBewerberByKennung(kennung));
  }

  public Bewerber initialiseEditBewerber(String kennung) {
    Bewerber b = modelService.load(bewerberRepository.findBewerberByKennung(kennung));
    return (b == null) ? new Bewerber(new Karriere(), new Personalien(), new Praeferenzen()) : b;
  }

  public boolean bewerbungExists(String kennung) {
    return findBewerberModelByKennung(kennung) != null;
  }

  public void removeBewerber(Bewerber bewerber) {
    bewerberRepository.delete(mappingService.load(bewerber));
  }

}