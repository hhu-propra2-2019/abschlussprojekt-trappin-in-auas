package mops.services;

import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.ModulAuswahlDTO;
import mops.domain.models.*;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BewerberService implements IBewerberService {

  private transient BewerberRepository bewerberRepository;
  private transient DTOService mappingService;
  private transient ModelService modelService;

  public BewerberService(BewerberRepository bewerberRepository,
      DTOService mappingService, ModelService modelService) {
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
    BewerberDTO zuFindenderBewerber = bewerberRepository.findBewerberByKennung(b.getKennung());

    if (zuFindenderBewerber != null) {
      bewerberDTO.setId(zuFindenderBewerber.getId());
    }
    bewerberRepository.save(bewerberDTO);
  }

  public void addBewerber(Bewerber b, String kennung) {
    b.setKennung(kennung);
    b.getKarriere()
        .getImmatrikulationsStatus()
        .setStatus(!b.getKarriere()
            .getImmatrikulationsStatus()
            .isStatus()
        )
    ;
    BewerberDTO bewerberDTO = mappingService.load(b);
    BewerberDTO zuFindenderBewerber = bewerberRepository.findBewerberByKennung(kennung);

    if (zuFindenderBewerber != null) {
      bewerberDTO.setId(zuFindenderBewerber.getId());
    }
    bewerberRepository.save(bewerberDTO);
  }

  @Override
  public Bewerber findBewerberByKennung(String kennung) {
    return modelService.load(bewerberRepository.findBewerberByKennung(kennung));
  }

  @Override
  public List<Bewerber> findAlleBewerber() {
    List<BewerberDTO> bewerberDTOs = bewerberRepository.findAll();
    System.out.println(bewerberDTOs);
    return bewerberDTOs.stream().map(x -> modelService.load(x)).collect(Collectors.toList());
  }

  @Override
  public List<Bewerber> findAlleNichtVerteilteBewerber() {
    return bewerberRepository.findAll().stream().map(x -> modelService.load(x))
        .filter(x -> x.getVerteiltAn() == null || x.getVerteiltAn().size() == 0).collect(Collectors.toList());
  }

  public void verteile(String kennung, Dozent dozent) {
    BewerberDTO b = bewerberRepository.findBewerberByKennung(kennung);
    b.getVerteiltAn().add(mappingService.load(dozent));
    bewerberRepository.save(b);
  }

  public void verteilungEntfernen(String kennung, String dozentKennung) {
    BewerberDTO b = bewerberRepository.findBewerberByKennung(kennung);
    b.getVerteiltAn().removeIf(x -> x.getDozentKennung().equals(dozentKennung));
    bewerberRepository.save(b);
  }

  public List<Bewerber> findAlleVerteilteBewerber() {
    return bewerberRepository.findAll().stream().map(x -> modelService.load(x))
        .filter(x -> x.getVerteiltAn() != null && x.getVerteiltAn().size() != 0)
        .collect(Collectors.toList());
  }

  public List<Bewerber> findBewerberFuerDozent(String dozentKennung) {
    return bewerberRepository.findAll().stream()
        .filter(x -> modulAuswahlContainsDozent(x.getPraeferenzen().getModulAuswahl(), dozentKennung))
        .map(x -> modelService.load(x)).collect(Collectors.toList());
  }

  private boolean modulAuswahlContainsDozent(List<ModulAuswahlDTO> auswahlDTO,
      String dozentKennung) {
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

  public void removeAll() {
    bewerberRepository.deleteAll();
  }

}