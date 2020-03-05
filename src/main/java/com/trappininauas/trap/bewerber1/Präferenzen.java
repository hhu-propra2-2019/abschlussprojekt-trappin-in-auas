package com.trappininauas.trap.bewerber1;

import lombok.Data;

@Data
public class Präferenzen {
    private long PRAEFID;
    private int wunschStunden;
    private String modulauswahl;
    private String kommentar;
    private Einstellungtyp einstellungtyp;
    private  String einschraenkungen;
    private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
