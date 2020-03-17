package mops.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.*;
import mops.domain.models.*;

import org.springframework.stereotype.Service;

import mops.domain.services.IModelService;

@Service
public class ModelService implements IModelService {

    public ModelService() {

    }

    @Override
    public Karriere load(KarriereDTO karriereDTO) {
        return new Karriere(karriereDTO.getArbeitserfahrung(), load(karriereDTO.getImmartikulationsStatus()),
                load(karriereDTO.getFachAbschluss()));
    }

    public StudiengangAbschluss load(StudiengangAbschlussDTO fachAbschluss) {
        return new StudiengangAbschluss(fachAbschluss.getStudiengang(), fachAbschluss.getAbschluss(), fachAbschluss.getUni());
    }

    public ImmartikulationsStatus load(ImmartikulationsStatusDTO statusDTO) {
        return new ImmartikulationsStatus(statusDTO.isStatus(), statusDTO.getFachrichtung());
    }

    @Override
    public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO) {
        return new ModulAuswahl(loadModul(modulAuswahlDTO.getModul()), modulAuswahlDTO.getPrioritaet(), modulAuswahlDTO.getNote());
    }

    @Override
    public Personalien load(PersonalienDTO pDTO) {
        if(pDTO == null){
            return null;
        }
        Adresse adresse = loadAdresse(pDTO);
        return new Personalien(adresse, pDTO.getUnikennung(), pDTO.getName(), pDTO.getVorname(), pDTO.getGeburtsdatum(),
                pDTO.getAlter(), pDTO.getGeburtsort(), pDTO.getNationalitaet());
    }


    public ModulAuswahl loadModulAuswahl(ModulAuswahlDTO modulAuswahlDTO) {
        return new ModulAuswahl(loadModul(modulAuswahlDTO.getModul()), modulAuswahlDTO.getPrioritaet(), modulAuswahlDTO.getNote());
    }

    public Adresse loadAdresse(PersonalienDTO personalienDTO) {
        return new Adresse(personalienDTO.getAdresse().getPLZ(), personalienDTO.getAdresse().getWohnort(),
                personalienDTO.getAdresse().getStraße(), personalienDTO.getAdresse().getHausnummer());
    }

    public BerufModul loadBerufModul(BerufModulDTO berufModulDTO) {
        return new BerufModul(berufModulDTO.getBeruf(), loadModul(berufModulDTO.getModul()));

    }

  @Override
  public Praeferenzen load(PraeferenzenDTO pDTO) {
    List<ModulAuswahl> modulAuswahl = pDTO.getModulAuswahl().stream().map(this::load).collect(Collectors.toList());
    return new Praeferenzen(pDTO.getMaxWunschStunden(), pDTO.getMinWunschStunden(), modulAuswahl,
        pDTO.getKommentar(), pDTO.getEinstiegTyp(), pDTO.getEinschraenkungen(),
        loadBerufModul(pDTO.getBerufModul()), pDTO.getTutorenSchulungTeilnahme());
  }


  public Modul loadModul(ModulDTO modulDTO) {
    return new Modul(modulDTO.getModul(), new Dozent(modulDTO.getDozentMail(), modulDTO.getDozentName()));
  }

  public ModulDTO loadModulDTO(Modul modul) {
    return new ModulDTO(modul.getModulName(), modul.getDozent().getDozentMail(), modul.getDozent().getDozentName());
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")  //Fix gradle bug: False positive  https://github.com/pmd/pmd/issues/387
  public List<Modul> loadModulList(List<ModulDTO> dtoList) {
    List<Modul> modulist = new LinkedList<>();
    for (ModulDTO modulDTO : dtoList) {
      modulist.add(loadModul(modulDTO));
    }
    return modulist;
  }

}