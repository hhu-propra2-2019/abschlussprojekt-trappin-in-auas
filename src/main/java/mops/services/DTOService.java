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

  public ModulDTO load(Modul modul) {
    // nicht "new" moduldto, sondern find moduldto
    List<ModulDTO> modulDTOs = modulRepository.findByModulAndDozentMail(modul.getModulName(),
        modul.getDozent().getDozentMail());
    System.out.println("===========================");
    System.out.println("Modul find by modulname und dozentmail:");
    System.out.println(modulDTOs);
    return modulDTOs.get(0);
  }

  public ModulAuswahlDTO load(ModulAuswahl modulAuswahl) {
    return new ModulAuswahlDTO(load(modulAuswahl.getModul()), modulAuswahl.getPrioritaet());
  }

  public BerufModulDTO load(BerufModul berufModul) {
    System.out.println("ich mache probleme");
    return new BerufModulDTO(berufModul.getBeruf(), load(berufModul.getModul()));
  }

  public AdresseDTO load(Adresse adresse) {
    return new AdresseDTO(adresse.getPLZ(), adresse.getWohnort(), adresse.getStraße(), adresse.getHausnummer());
  }

  public ImmartikulationsStatusDTO load(ImmartikulationsStatus imStatus) {
    return new ImmartikulationsStatusDTO(imStatus.isStatus(), imStatus.getFachrichtung());
  }

  public StudiengangAbschlussDTO load(StudiengangAbschluss studiengangAbschluss) {
    return new StudiengangAbschlussDTO(studiengangAbschluss.getAbschluss(), studiengangAbschluss.getStudiengang());
  }

  public PersonalienDTO load(Personalien personalien) {
    return new PersonalienDTO(load(personalien.getAdresse()), personalien.getUnikennung(), personalien.getName(),
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
        praeferenzen.getEinschraenkungen(), null, praeferenzen.getTutorenSchulungTeilnahme());
  }

  public BewerberDTO load(Bewerber bewerber) {
    List<VerteilungDTO> verteiltAn = (bewerber.getVerteiltAn() == null) ? null : load(bewerber.getVerteiltAn());
    BewerberDTO bewerberDTO = new BewerberDTO(load(bewerber.getPersonalien()), load(bewerber.getKarriere()),
        load(bewerber.getPraeferenzen()), verteiltAn);
    bewerberDTO.setErstelltVon(bewerber.getErstelltVon());
    return bewerberDTO;
  }

  public VerteilungDTO load(Dozent dozent) {
    return new VerteilungDTO(dozent.getDozentName(), dozent.getDozentMail());
  }

  private List<VerteilungDTO> load(List<Dozent> verteiltAn) {
    return verteiltAn.stream().map(x -> load(x)).collect(Collectors.toList());
  }

  private List<ModulAuswahlDTO> loadList(Praeferenzen praeferenzen) {
    System.out.println("/////////////////////////////");
    System.out.println("meine modulauswahls:");
    System.out.println(praeferenzen.getModulAuswahl());
    return praeferenzen.getModulAuswahl().stream().map(this::load).collect(Collectors.toList());
  }

  public DozentPraeferenzDTO load(DozentPraeferenz dPraeferenz) {
    return new DozentPraeferenzDTO(dPraeferenz.getBewerberKennung(), dPraeferenz.getDozentKennung(),
        dPraeferenz.getPraeferenz());
  }
}