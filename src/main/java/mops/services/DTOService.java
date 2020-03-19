package mops.services;


import java.util.List;
import java.util.stream.Collectors;
import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.services.IDTOService;
import org.springframework.stereotype.Service;

@Service
public class DTOService implements IDTOService {
  /*
   * Mapping Models -> DTO
   * Für Bewerbungsbogen -> Datenbank
   * */

  public ModulDTO load(Modul modul){
    return new ModulDTO(modul.getModulName(),modul.getDozent().getDozentName(),modul.getDozent().getDozentMail());
  }

  public ModulAuswahlDTO load(ModulAuswahl modulAuswahl){
    return new ModulAuswahlDTO(load(modulAuswahl.getModul()),modulAuswahl.getPrioritaet());
  }

  public BerufModulDTO load(BerufModul berufModul){
    return new BerufModulDTO(berufModul.getBeruf(),load(berufModul.getModul()));
  }

  public AdresseDTO load(Adresse adresse){
    return new AdresseDTO(adresse.getPLZ(),adresse.getWohnort(),adresse.getStrasse(),adresse.getHausnummer());
  }

  public ImmartikulationsStatusDTO load(ImmartikulationsStatus imStatus){
    return new ImmartikulationsStatusDTO(imStatus.isStatus(),imStatus.getFachrichtung());
  }

  public StudiengangAbschlussDTO load(StudiengangAbschluss studiengangAbschluss){
    return new StudiengangAbschlussDTO(studiengangAbschluss.getStudiengang(),studiengangAbschluss.getAbschluss());
  }

  public PersonalienDTO load(Personalien personalien){
    return new PersonalienDTO(load(personalien.getAdresse()),personalien.getUnikennung(),personalien.getName(),
        personalien.getVorname(),personalien.getGeburtsdatum(),personalien.getAlter(),personalien.getGeburtsort(),
        personalien.getNationalitaet());
  }

  public KarriereDTO load(Karriere karriere){
    return new KarriereDTO(karriere.getArbeitserfahrung(),load(karriere.getImmartikulationsStatus()),
        load(karriere.getFachAbschluss()));
  }

  public PraeferenzenDTO load(Praeferenzen praeferenzen){
    return new PraeferenzenDTO(praeferenzen.getMinWunschStunden(),praeferenzen.getMaxWunschStunden(),
        loadList(praeferenzen),praeferenzen.getKommentar(),praeferenzen.getEinstiegTyp(),
        praeferenzen.getEinschraenkungen(),load(praeferenzen.getBerufModul()),
        praeferenzen.getTutorenSchulungTeilnahme());
  }

  public BewerberDTO load(Bewerber bewerber){
    return new BewerberDTO(load(bewerber.getPersonalien()),load(bewerber.getKarriere()),
        load(bewerber.getPraeferenzen()),bewerber.getVerteiltAn());
  }

   public List<ModulAuswahlDTO> loadList(Praeferenzen praeferenzen){
    return praeferenzen.getModulAuswahl().stream().map(this::load).collect(Collectors.toList());
  }
}
