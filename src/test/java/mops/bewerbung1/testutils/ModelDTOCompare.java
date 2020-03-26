package mops.bewerbung1.testutils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import mops.domain.database.dto.*;
import mops.domain.models.*;

public class ModelDTOCompare {
  public ModelDTOCompare() {

  }

  /**
   * Performs assertions on attributes including nested objects basically a
   * substitute for assertEquals(bewerber, bewerberDTO)
   * 
   * because we are comparing 2 different classes we can't override the equals
   * method
   * 
   * @param bewerber
   * @param bewerberDTO
   */
  public void compare(Bewerber bewerber, BewerberDTO bewerberDTO) {
    compare(bewerber.getPersonalien(), bewerberDTO.getPersonalien());
    compare(bewerber.getKarriere(), bewerberDTO.getKarriere());
    compare(bewerber.getPraeferenzen(), bewerberDTO.getPraeferenzen());
  }

  public void compare(Praeferenzen praeferenzen, PraeferenzenDTO praeferenzenDTO) {
    compare(praeferenzen.getModulAuswahl(), praeferenzenDTO.getModulAuswahl());

    assertEquals(praeferenzen.getEinschraenkungen(), praeferenzenDTO.getEinschraenkungen());
    assertEquals(praeferenzen.getEinstiegTyp(), praeferenzenDTO.getEinstiegTyp());
    assertEquals(praeferenzen.getKommentar(), praeferenzenDTO.getKommentar());
    assertEquals(praeferenzen.getMaxWunschStunden(), praeferenzenDTO.getMaxWunschStunden());
    assertEquals(praeferenzen.getMinWunschStunden(), praeferenzenDTO.getMinWunschStunden());
    assertEquals(praeferenzen.getTutorenSchulungTeilnahme(), praeferenzenDTO.getTutorenSchulungTeilnahme());
  }

  private void compare(List<ModulAuswahl> modulAuswahl, List<ModulAuswahlDTO> modulAuswahlDTO) {
    for(int i = 0; i < modulAuswahl.size(); i++){
      compare(modulAuswahl.get(i).getModul(), modulAuswahlDTO.get(i).getModul());

      assertEquals(modulAuswahl.get(i).getBeruf(), modulAuswahlDTO.get(i).getBeruf());
      assertEquals(modulAuswahl.get(i).getNote(), modulAuswahlDTO.get(i).getNote());
      assertEquals(modulAuswahl.get(i).getPrioritaet(), modulAuswahlDTO.get(i).getPrioritaet());
    }
  }

  public void compare(Modul modul, ModulDTO modulDTO) {
    assertEquals(modul.getDozent().getDozentMail(), modulDTO.getDozentMail());
    assertEquals(modul.getDozent().getDozentName(), modulDTO.getDozentName());
    assertEquals(modul.getModulName(), modul.getModulName());
  }

  public void compare(Karriere karriere, KarriereDTO karriereDTO) {
    compare(karriere.getFachAbschluss(), karriereDTO.getFachAbschluss());
    compare(karriere.getImmatrikulationsStatus(), karriereDTO.getImmartikulationsStatus());

    assertEquals(karriere.getArbeitserfahrung(), karriereDTO.getArbeitserfahrung());
  }

  public void compare(ImmatrikulationsStatus immartikulationsStatus,
      ImmartikulationsStatusDTO immartikulationsStatusDTO) {
    assertEquals(immartikulationsStatus.getFachrichtung(), immartikulationsStatusDTO.getFachrichtung());
    assertEquals(immartikulationsStatus.isStatus(), immartikulationsStatusDTO.isStatus());
  }

  public void compare(StudiengangAbschluss fachAbschluss, StudiengangAbschlussDTO fachAbschlussDTO) {
    assertEquals(fachAbschluss.getAbschluss(), fachAbschlussDTO.getAbschluss());
    assertEquals(fachAbschluss.getStudiengang(), fachAbschlussDTO.getStudiengang());
    assertEquals(fachAbschluss.getUni(), fachAbschlussDTO.getUni());
  }

  public void compare(Personalien personalien, PersonalienDTO personalienDTO) {
    compare(personalien.getAdresse(), personalienDTO.getAdresse());

    assertEquals(personalien.getAlter(), personalienDTO.getAlter());
    assertEquals(personalien.getGeburtsdatum(), personalienDTO.getGeburtsdatum());
    assertEquals(personalien.getGeburtsort(), personalien.getGeburtsort());
    assertEquals(personalien.getName(), personalien.getName());
    assertEquals(personalien.getVorname(), personalien.getVorname());
    assertEquals(personalien.getNationalitaet(), personalien.getNationalitaet());
  }

  public void compare(Adresse adresse, AdresseDTO adresseDTO) {
    assertEquals(adresse.getHausnummer(), adresseDTO.getHausnummer());
    assertEquals(adresse.getPLZ(), adresseDTO.getPLZ());
    assertEquals(adresse.getStrasse(), adresseDTO.getStrasse());
    assertEquals(adresse.getWohnOrt(), adresseDTO.getWohnort());
  }
}