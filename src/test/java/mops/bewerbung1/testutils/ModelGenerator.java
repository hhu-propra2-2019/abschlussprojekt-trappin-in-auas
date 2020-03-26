package mops.bewerbung1.testutils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import mops.domain.models.*;

public class ModelGenerator {

  private static List<String> kennungen = Arrays.asList("aliwe122", "yoawer220", "omsaa130");

  private static List<String> PLZs = Arrays.asList("40123", "41222", "40235", "41223", "52352", "40216");
  private static List<String> strassen = Arrays.asList("Musterstrasse", "Samplestrasse", "Beispielallee");

  public Bewerber genrateBewerber() {
    // karriere, personalien, praeferenzen, kennung, verteiltAn, dozentPraeferenz
    Bewerber bewerber = new Bewerber();
    bewerber.setKarriere(generateKarriere());
    bewerber.setPersonalien(generatePersonalien());
    bewerber.setPraeferenzen(generatePraeferenz());

    bewerber.setKennung(kennungen.get((int) (Math.random() * kennungen.size())));
    return bewerber;
  }

  private Praeferenzen generatePraeferenz() {
    Praeferenzen pref = new Praeferenzen();
    pref.setEinschraenkungen("random einschraenkung");
    pref.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
    pref.setKommentar("random kommentar");
    pref.setMinWunschStunden((int)(Math.random()*10) + 7);
    pref.setMaxWunschStunden((int)(Math.random()*10) + 7);
    pref.setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILGENOMMEN);
    return pref;
  }

  private Personalien generatePersonalien() {
    Personalien personalien = new Personalien();
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

  private Adresse generateAdresse() {
    Adresse adresse = new Adresse();
    adresse.setHausnummer("" + (int) (Math.random() * 100));
    adresse.setPLZ(PLZs.get((int) (Math.random() * PLZs.size())));
    adresse.setStrasse(strassen.get((int) (Math.random() * strassen.size())));
    adresse.setWohnOrt("beispielstadt");
    return adresse;
  }

  public Karriere generateKarriere() {
    Karriere karriere = new Karriere();
    karriere.setArbeitserfahrung("random arbeitserfahrung");
    karriere.setFachAbschluss(generateAbschluss());
    karriere.setImmartikulationsStatus(generateImmatrikulation());
    return karriere;
  }

  public ImmartikulationsStatus generateImmatrikulation() {
    ImmartikulationsStatus status = new ImmartikulationsStatus();
    status.setFachrichtung("random fachrichtung");
    status.setStatus(Math.random() > 0.5);
    return status;
  }

  public StudiengangAbschluss generateAbschluss() {
    StudiengangAbschluss abschluss = new StudiengangAbschluss();
    abschluss.setAbschluss("random abschluss");
    abschluss.setStudiengang("random studiengang");
    abschluss.setUni("random uni");
    return abschluss;
  }

  public static Date between(Date startInclusive, Date endExclusive) {
    long startMillis = startInclusive.getTime();
    long endMillis = endExclusive.getTime();
    long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

    return new Date(randomMillisSinceEpoch);
  }
}