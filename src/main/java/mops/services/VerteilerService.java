package mops.services;

import java.util.LinkedList;
import java.util.List;
import mops.domain.models.Modul;
import mops.domain.models.ModuleMitVerteiltenAnzahl;
import mops.domain.repositories.VerteilungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerteilerService {

  @Autowired
  private transient VerteilungRepo verteilungRepo;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public List<ModuleMitVerteiltenAnzahl> getListModulMitAnzahlVerteilten(List<Modul> listModul){
    List<ModuleMitVerteiltenAnzahl> moduleMitVerteiltenAnzahlList = new LinkedList<>();
    for(Modul modul : listModul){
      ModuleMitVerteiltenAnzahl moduleMitVerteiltenAnzahl = buildModulMitAnzahlVerteilten(modul);
      moduleMitVerteiltenAnzahlList.add(moduleMitVerteiltenAnzahl);
    }
    return moduleMitVerteiltenAnzahlList;
  }

  private ModuleMitVerteiltenAnzahl buildModulMitAnzahlVerteilten(Modul modul) {
    int anzahlVerteilte = verteilungRepo.countVerteilungDTOByDozentKennungEquals(modul.getDozent().getDozentMail());
    return new ModuleMitVerteiltenAnzahl(modul, anzahlVerteilte);
  }





}
