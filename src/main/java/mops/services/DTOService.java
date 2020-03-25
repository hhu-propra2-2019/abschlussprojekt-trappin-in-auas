package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mops.domain.database.dto.*;
import mops.domain.models.*;
import mops.domain.repositories.ModulRepository;
import mops.domain.services.IDTOService;


@Service
public class DTOService implements IDTOService {
  /*
   * Mapping Models -> DTO Für Bewerbungsbogen -> Datenbank
   */
  @Autowired
  private transient ModulRepository modulRepository;

  public DTOService(){

  }

  public DTOService(ModulRepository modulRepository){
    this.modulRepository = modulRepository;
  }

  public ModulDTO load(Modul modul) {
    List<ModulDTO> modulDTOs = modulRepository.findByModulNameAndDozentMail(modul.getModulName(),
        modul.getDozent().getDozentMail());
    return (modulDTOs.size() > 0) ? modulDTOs.get(0)
        : new ModulDTO(modul.getModulName(), modul.getDozent().getDozentName(), modul.getDozent().getDozentMail());
  }

  public ModulAuswahlDTO load(ModulAuswahl modulAuswahl) {
    return new ModulAuswahlDTO(load(modulAuswahl.getModul()), modulAuswahl.getPrioritaet(),modulAuswahl.getNote(), modulAuswahl.getBeruf());
  }

  public AdresseDTO load(Adresse adresse){
    return new AdresseDTO(adresse.getPLZ(),adresse.getWohnort(),adresse.getStrasse(),adresse.getHausnummer());

  }

  public ImmartikulationsStatusDTO load(ImmartikulationsStatus imStatus) {
    return new ImmartikulationsStatusDTO(imStatus.isStatus(), imStatus.getFachrichtung());
  }

  public StudiengangAbschlussDTO load(StudiengangAbschluss studiengangAbschluss){
    return new StudiengangAbschlussDTO(studiengangAbschluss.getStudiengang(),studiengangAbschluss.getAbschluss(),
        studiengangAbschluss.getUni());

  }

  public PersonalienDTO load(Personalien personalien) {
    return new PersonalienDTO(load(personalien.getAdresse()), personalien.getName(),
        personalien.getVorname(), personalien.getGeburtsdatum(), personalien.getAlter(), personalien.getGeburtsort(),
        personalien.getNationalitaet());
  }

  public KarriereDTO load(Karriere karriere) {
    return new KarriereDTO(karriere.getArbeitserfahrung(), load(karriere.getImmartikulationsStatus()),
        load(karriere.getFachAbschluss()));
  }

  public PraeferenzenDTO load(Praeferenzen praeferenzen) {
    return new PraeferenzenDTO(praeferenzen.getMinWunschStunden(), praeferenzen.getMaxWunschStunden(),
        loadList(praeferenzen), praeferenzen.getKommentar(), praeferenzen.getEinstiegTyp(),
        praeferenzen.getEinschraenkungen(), praeferenzen.getTutorenSchulungTeilnahme());
  }

  public BewerberDTO load(Bewerber bewerber) {
    List<VerteilungDTO> verteiltAn = (bewerber.getVerteiltAn() == null) ? null : load(bewerber.getVerteiltAn());
    List<DozentPraeferenzDTO> dozentPraeferenz = (bewerber.getDozentPraeferenz() == null) ? null : loadDozentPraeferenzList(bewerber.getDozentPraeferenz());
    BewerberDTO bewerberDTO = new BewerberDTO(load(bewerber.getPersonalien()), load(bewerber.getKarriere()),
        load(bewerber.getPraeferenzen()), verteiltAn, dozentPraeferenz);
    bewerberDTO.setKennung(bewerber.getKennung());
    return bewerberDTO;
  }

  public VerteilungDTO load(Dozent dozent) {
    return new VerteilungDTO(dozent.getDozentName(), dozent.getDozentMail());
  }

  private List<VerteilungDTO> load(List<Dozent> verteiltAn) {
    return verteiltAn.stream().map(x -> load(x)).collect(Collectors.toList());
  }

  private List<DozentPraeferenzDTO> loadDozentPraeferenzList(List<DozentPraeferenz> dozentPraeferenzs){
    return  dozentPraeferenzs.stream().map(x -> load(x)).collect(Collectors.toList());
  }

  public List<ModulAuswahlDTO> loadList(Praeferenzen praeferenzen) {
    return praeferenzen.getModulAuswahl().stream().map(this::load).collect(Collectors.toList());
  }

  public DozentPraeferenzDTO load(DozentPraeferenz dPraeferenz) {
    return new DozentPraeferenzDTO(dPraeferenz.getBewerberKennung(), dPraeferenz.getDozentKennung(),
        dPraeferenz.getPraeferenz());
  }
}