package mops.bewerbung1.testutils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import mops.domain.database.dto.*;
import mops.domain.models.Beruf;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.TutorenSchulungTeilnahme;

public class DTOGenerator {

  private static List<String> kennungen = Arrays.asList("aliwe122", "yoawer220", "omsaa130");

  private static List<String> PLZs = Arrays.asList("40123", "41222", "40235", "41223", "52352", "40216");
  private static List<String> strassen = Arrays.asList("Musterstrasse", "Samplestrasse", "Beispielallee");
  private static List<String> arbeitserfahrungen = Arrays.asList("bei Apple Store gearbeitet.", "Hackathon gewonnen",
      "1.0 in Programmierung geschrieben");

  /**
   * generates randomized Bewerber model
   * 
   * @return Bewerber with nested random attributes
   */
  public BewerberDTO generateBewerber() {
    BewerberDTO bewerber = new BewerberDTO();
    bewerber.setKarriere(generateKarriere());
    bewerber.setPersonalien(generatePersonalien());
    bewerber.setPraeferenzen(generatePraeferenz());

    bewerber.setKennung(kennungen.get((int) (Math.random() * kennungen.size())));
    System.out.println("generated bewerber with kennung: " + bewerber.getKennung());
    return bewerber;
  }

  /**
   * generates randomized Praeferenzen model
   * 
   * @return Praeferenzen with nested random attributes
   */
  public PraeferenzenDTO generatePraeferenz() {
    PraeferenzenDTO pref = new PraeferenzenDTO();
    pref.setEinschraenkungen("random einschraenkung");
    pref.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
    pref.setKommentar("random kommentar");
    pref.setMinWunschStunden((int) (Math.random() * 10) + 7);
    pref.setMaxWunschStunden((int) (Math.random() * 10) + 7);
    pref.setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILGENOMMEN);
    pref.setModulAuswahl(new LinkedList<>());
    return pref;
  }

  /**
   * generates randomized Personalien model
   * 
   * @return Personalien with nested random attributes
   */
  public PersonalienDTO generatePersonalien() {
    PersonalienDTO personalien = new PersonalienDTO();
    personalien.setAdresse(generateAdresse());
    personalien.setAlter(15 + (int) (Math.random() * 50));

    // random date between 100 years ago and 10 days ago
    long aDay = TimeUnit.DAYS.toMillis(1);
    long now = new Date().getTime();
    Date hundredYearsAgo = new Date(now - aDay * 365 * 100);
    Date tenDaysAgo = new Date(now - aDay * 10);
    Date random = between(hundredYearsAgo, tenDaysAgo);

    personalien.setGeburtsdatum(random);
    personalien.setGeburtsort("random geburtsort");
    personalien.setName("random name");
    personalien.setNationalitaet("random nation");
    personalien.setVorname("random vorname");
    return personalien;
  }

  /**
   * generates randomized Adresse model
   * 
   * @return Adresse with random attributes
   */
  public AdresseDTO generateAdresse() {
    AdresseDTO adresse = new AdresseDTO();
    adresse.setHausnummer("" + (int) (Math.random() * 100));
    adresse.setPLZ(PLZs.get((int) (Math.random() * PLZs.size())));
    adresse.setStrasse(strassen.get((int) (Math.random() * strassen.size())));
    adresse.setWohnort("beispielstadt");
    return adresse;
  }

  /**
   * generates randomized Karriere model
   * 
   * @return Karriere with nested random attributes
   */
  public KarriereDTO generateKarriere() {
    KarriereDTO karriere = new KarriereDTO();
    karriere.setArbeitserfahrung(arbeitserfahrungen.get((int) (Math.random() * arbeitserfahrungen.size())));
    karriere.setFachAbschluss(generateAbschluss());
    karriere.setImmartikulationsStatus(generateImmatrikulation());
    return karriere;
  }

  /**
   * generates randomized ImmartikulationsStatus model
   * 
   * @return ImmartikulationsStatus with random attributes
   */
  public ImmatrikulationsStatusDTO generateImmatrikulation() {
    ImmatrikulationsStatusDTO status = new ImmatrikulationsStatusDTO();
    status.setFachrichtung("random fachrichtung");
    status.setStatus(Math.random() > 0.5);
    return status;
  }

  /**
   * generates randomized StudiengangAbschluss model
   * 
   * @return StudiengangAbschluss with random attributes
   */
  public StudiengangAbschlussDTO generateAbschluss() {
    StudiengangAbschlussDTO abschluss = new StudiengangAbschlussDTO();
    abschluss.setAbschluss("random abschluss");
    abschluss.setStudiengang("random studiengang");
    abschluss.setUni("random uni");
    return abschluss;
  }

  private static List<Double> noten = Arrays.asList(1.0, 1.3, 1.7, 2.0, 2.3, 2.7, 3.0, 3.3, 3.7, 3.0, 3.3, 3.7, 4.0);

  public ModulAuswahlDTO generateModulAuswahl() {
    return new ModulAuswahlDTO(generateModul(), 1 + (int) Math.random() * 3,
        noten.get((int) (Math.random() * noten.size())), Beruf.Tutor);
  }

  private static List<String> module = Arrays.asList("RDB", "ProPra2", "AlDat", "Programmierung");

  /**
   * generates randomized Modul model
   * 
   * @return Modul with nested random attributes
   */
  public ModulDTO generateModul() {
    VerteilungDTO dozent = generateDozent();
    return new ModulDTO(module.get((int) (Math.random() * module.size())), dozent.getDozentName(),
        dozent.getDozentKennung());
  }

  private static List<String> dozentNamen = Arrays.asList("Jens Bendisposto", "Stephan Mueller", "Michael Schoetner",
      "Stefan Harmeling");
  private static List<String> dozentMails = Arrays.asList("jens@hhu.de", "stephan@hhu.de", "shoetner@hhu.de",
      "stefan@hhu.de");

  /**
   * generates randomized Dozent model
   * 
   * @return Dozent with random attributes
   */
  public VerteilungDTO generateDozent() {
    return new VerteilungDTO(dozentMails.get((int) (Math.random() * dozentMails.size())),
        dozentNamen.get((int) (Math.random() * dozentNamen.size())));
  }

  private static Date between(Date startInclusive, Date endExclusive) {
    long startMillis = startInclusive.getTime();
    long endMillis = endExclusive.getTime();
    long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

    return new Date(randomMillisSinceEpoch);
  }

  public DozentPraeferenzDTO generateDozentPraeferenz() {
    return new DozentPraeferenzDTO(new RandomListObject<String>().getRandomObject(kennungen),
        new RandomListObject<String>().getRandomObject(dozentMails), (int) Math.random() * 4);
  }
}

class RandomListObject<T> {
  public T getRandomObject(List<T> list) {
    return list.get((int) (Math.random() * list.size()));
  }
}