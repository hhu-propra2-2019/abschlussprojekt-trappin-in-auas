package mops;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import mops.domain.models.Adresse;
import mops.domain.models.Beruf;
import mops.domain.models.Bewerber;
import mops.domain.models.Dozent;
import mops.domain.models.EinstiegTyp;
import mops.domain.models.ImmartikulationsStatus;
import mops.domain.models.Karriere;
import mops.domain.models.Modul;
import mops.domain.models.ModulAuswahl;
import mops.domain.models.Personalien;
import mops.domain.models.Praeferenzen;
import mops.domain.models.StudiengangAbschluss;
import mops.domain.models.TutorenSchulungTeilnahme;
import mops.services.PDFService;

public class PDF {
  public static void main(String[] args) throws Exception {
    Bewerber b = new Bewerber();
    Personalien personalien = new Personalien();
    personalien.setAlter(18);
    try {
      personalien.setGeburtsdatum(
          Date.from(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("31.05.1999").toInstant()));
    } catch (Exception e) {
      System.out.println("Geburtsdatum falsches Format");
    }
    personalien.setGeburtsort("Duesseldorf");
    personalien.setName("Der Marrokaner");
    personalien.setNationalitaet("Deutschland");
    personalien.setUnikennung("mawin125");
    personalien.setVorname("Oussama");
    personalien.setAdresse(new Adresse("40235", "Duesseldorf", "Porschestraße", "17"));

    Praeferenzen praeferenzen = new Praeferenzen();
    Modul propraModul = new Modul();
    propraModul.setModulName("ProPra");
    propraModul.setDozent(new Dozent("jens@hhu.de", "jens"));

    praeferenzen.setEinschraenkungen("Keine");
    praeferenzen.setEinstiegTyp(EinstiegTyp.NEUEINSTIEG);
    praeferenzen.setKommentar("Ich mag ProPra");
    praeferenzen.setMaxWunschStunden(14);
    praeferenzen.setMinWunschStunden(7);

    List<ModulAuswahl> modulAuswahl = new ArrayList<>();
    modulAuswahl.add(new ModulAuswahl(propraModul, 2, 1.0, Beruf.Korrektor));

    praeferenzen.setModulAuswahl(modulAuswahl);
    praeferenzen.setTutorenSchulungTeilnahme(TutorenSchulungTeilnahme.TEILNAHME);

    Karriere karriere = new Karriere();
    karriere.setArbeitserfahrung("Viel");
    karriere.setFachAbschluss(new StudiengangAbschluss("Informatik", "Bachelor", "HHU"));
    karriere.setImmartikulationsStatus(new ImmartikulationsStatus(true, "Informatik"));

    b.setPersonalien(personalien);
    b.setPraeferenzen(praeferenzen);
    b.setKarriere(karriere);

    PDFService pdfService = new PDFService();
    pdfService.fileDirectory(b);
    pdfService.fillPDF(b, pdfService.fileDirectory(b));
  }
}
