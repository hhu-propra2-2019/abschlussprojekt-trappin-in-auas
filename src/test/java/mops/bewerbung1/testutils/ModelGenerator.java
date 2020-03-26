package mops.bewerbung1.testutils;

import java.util.*;

import mops.domain.models.*;

public class ModelGenerator {

  private static List<String> kennungen = Arrays.asList("aliwe122", "yoawer220", "omsaa130");

  private static List<String> PLZs = Arrays.asList("40123", "41222", "40235", "41223", "52352", "40335", "40237", "40216");
  private static List<String> strassen = Arrays.asList("Musterstrasse", "Samplestrasse", "Beispielallee");


  public Bewerber genrateBewerber() {
    // karriere, personalien, praeferenzen, kennung, verteiltAn, dozentPraeferenz
    Bewerber bewerber = new Bewerber();
    bewerber.setKarriere(generateKarriere());
    bewerber.setPersonalien(generatePersonalien());
    bewerber.setKennung(kennungen.get((int)(Math.random() * kennungen.size())));

    return bewerber;
  }

  private Personalien generatePersonalien() {
    Personalien personalien = new Personalien();
    personalien.setAdresse(generateAdresse());
    personalien.setAlter((int) (Math.random()*150));
    return personalien;
  }

  private Adresse generateAdresse() {
    Adresse adresse = new Adresse();
    adresse.setHausnummer("" + (int)(Math.random() * 100));
    adresse.setPLZ(PLZs.get((int)(Math.random() * PLZs.size())));
    adresse.setStrasse(strassen.get((int)(Math.random() * strassen.size())));
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
}