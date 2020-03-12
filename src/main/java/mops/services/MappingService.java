package mops.services;

import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.repositories.*;

import org.springframework.stereotype.Service;

import mops.domain.services.IMappingService;

@Service
public class MappingService implements IMappingService {
    private BestandeneModuleRepo bestandeneModule;
    private ModulAuswahlRepo modulAuswahl;
    private KarriereRepo karriere;
    private PraeferenzenRepo praeferenzen;
    private PersonalienRepo personalien;

    public MappingService(BestandeneModuleRepo bestandeneModule, ModulAuswahlRepo modulAuswahl, KarriereRepo karriere,
                          PraeferenzenRepo praeferenzen, PersonalienRepo personalien){
        this.bestandeneModule = bestandeneModule;
        this.modulAuswahl = modulAuswahl;
        this.karriere = karriere;
        this.praeferenzen = praeferenzen;
        this.personalien = personalien;
    }

    @Override
    public BestandeneModule load(BestandeneModuleDTO bestandeneModuleDTO) {
        return null;
    }

    @Override
    public Karriere load(KarriereDTO karriereDTO) {
        return null;
    }

    //TODO praeferenzen
    @Override
    public ModulAuswahl load(ModulAuswahlDTO modulAuswahlDTO) {
        return new ModulAuswahl(loadModul(modulAuswahlDTO),modulAuswahlDTO.getPrioritaet());
    }

    @Override
    public Personalien load(PersonalienDTO pDTO) {
        Adresse adresse = loadAdresse(pDTO);
        return new Personalien(adresse,pDTO.getUnikennung(),pDTO.getName(),pDTO.getVorname(),pDTO.getGeburtsdatum(),
                                pDTO.getAlter(),pDTO.getGeburtsort(),pDTO.getNationalitaet());
    }

    @Override
    public Praeferenzen load(PraeferenzenDTO pDTO) {
        List<ModulAuswahl> modulAuswahl = pDTO.getModulAuswahl().stream().map(this::load).collect(
            Collectors.toList());
        return null;/*new Praeferenzen(pDTO.getMinWunschStunden(),pDTO.getMaxWunschStunden(),modulAuswahl,
            pDTO.getKommentar(),pDTO.getEinstiegTyp(),pDTO.getEinschraenkungen(),
            pDTO.getBerufModul(),pDTO.getTutorenSchulungTeilnahme());*/
    }

    private Modul loadModul(ModulAuswahlDTO modulAuswahlDTO){
        return new Modul(modulAuswahlDTO.getModul().getModul());
    }
    private Adresse loadAdresse(PersonalienDTO personalienDTO){
        return new Adresse(personalienDTO.getAdresse().getPLZ(), personalienDTO.getAdresse().getWohnort(),
            personalienDTO.getAdresse().getStraße(), personalienDTO.getAdresse().getHausnummer());
    }
   /* private BerufModul loadBerufModul(BerufModulDTO berufModulDTO){

    }*/
    
}