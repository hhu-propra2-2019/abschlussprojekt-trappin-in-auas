package mops.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.*;
import mops.domain.models.*;

import mops.domain.services.IModelService;
import org.springframework.stereotype.Service;


@Service
public class ModelService implements IModelService {

  public ModelService() {

  }

  public Bewerber load(BewerberDTO bewerberDTO) {
    if (bewerberDTO == null) {
      return null;
    }
    List<DozentPraeferenz> dPref = (bewerberDTO.getDozentPraeferenz() == null) ? new LinkedList<>()
        : bewerberDTO.getDozentPraeferenz().stream().map(x -> load(x)).collect(Collectors.toList());

    return new Bewerber(load(bewerberDTO.getKarriere()), load(bewerberDTO.getPersonalien()),
        load(bewerberDTO.getPraeferenzen()), bewerberDTO.getKennung(),
        bewerberDTO.getVerteiltAn().stream().map(x -> load(x)).collect(Collectors.toList()), dPref);
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Fix gradle bug: False positive
  // https://github.com/pmd/pmd/issues/387
  public List<Bewerber> loadBewerberList(List<BewerberDTO> bewerberDTOList) {
    List<Bewerber> bewerberList = new LinkedList<>();
    for (BewerberDTO bewerberDTO : bewerberDTOList) {
      bewerberList.add(load(bewerberDTO));
    }
    return bewerberList;
  }


  public Dozent load(VerteilungDTO verteilungDTO){
    return new Dozent(verteilungDTO.getDozentKennung(), verteilungDTO.getDozentName());
  }

  @Override
  public Karriere load(KarriereDTO karriereDTO) {
    if(karriereDTO == null){
      return null;
    }
    return new Karriere(karriereDTO.getArbeitserfahrung(), load(karriereDTO.getImmartikulationsStatus()),
        load(karriereDTO.getFachAbschluss()));
  }

  public StudiengangAbschluss load(StudiengangAbschlussDTO fachAbschluss) {
    return new StudiengangAbschluss(fachAbschluss.getStudiengang(), fachAbschluss.getAbschluss(),
        fachAbschluss.getUni());
  }

  public ImmatrikulationsStatus load(ImmatrikulationsStatusDTO statusDTO) {
    return new ImmatrikulationsStatus(statusDTO.isStatus(), statusDTO.getFachrichtung());
  }

  @Override
  public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO) {
    if(modulAuswahlDTO == null){
      return null;
    }
    return new ModulAuswahl(loadModul(modulAuswahlDTO.getModul()), modulAuswahlDTO.getPrioritaet(),
        modulAuswahlDTO.getNote(), modulAuswahlDTO.getBeruf());
  }

  @Override
  public Personalien load(PersonalienDTO pDTO) {
    if(pDTO == null){
      return null;
    }
    Adresse adresse = loadAdresse(pDTO);
    return new Personalien(adresse, pDTO.getName(), pDTO.getVorname(), pDTO.getGeburtsdatum(),
        pDTO.getAlter(), pDTO.getGeburtsort(), pDTO.getNationalitaet());
  }

  public Adresse loadAdresse(PersonalienDTO personalienDTO) {
    return new Adresse(personalienDTO.getAdresse().getPLZ(), personalienDTO.getAdresse().getWohnort(),
        personalienDTO.getAdresse().getStrasse(), personalienDTO.getAdresse().getHausnummer());
  }

  @Override
  public Praeferenzen load(PraeferenzenDTO pDTO) {
    List<ModulAuswahl> modulAuswahl = pDTO.getModulAuswahl().stream().map(this::load).collect(Collectors.toList());
    return new Praeferenzen(pDTO.getMaxWunschStunden(), pDTO.getMinWunschStunden(), modulAuswahl, pDTO.getKommentar(),
        pDTO.getEinstiegTyp(), pDTO.getEinschraenkungen(),
        pDTO.getTutorenSchulungTeilnahme());
  }

  public Modul loadModul(ModulDTO modulDTO) {
    return new Modul(modulDTO.getModulName(), new Dozent(modulDTO.getDozentMail(), modulDTO.getDozentName()));
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis") // Fix gradle bug: False positive
                                                   // https://github.com/pmd/pmd/issues/387
  public List<Modul> loadModulList(List<ModulDTO> dtoList) {
    List<Modul> modulist = new LinkedList<>();
    for (ModulDTO modulDTO : dtoList) {
      modulist.add(loadModul(modulDTO));
    }
    return modulist;
  }

  @Override
  public DozentPraeferenz load(DozentPraeferenzDTO dozentPraeferenzDTO) {
    return new DozentPraeferenz(
        dozentPraeferenzDTO.getDozentMail(),
        dozentPraeferenzDTO.getBewerber(),
        dozentPraeferenzDTO.getPraeferenz());
  }
}