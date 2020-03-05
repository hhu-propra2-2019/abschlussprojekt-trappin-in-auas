package mops.model;


import lombok.Data;

@Data
public class Praeferenzen {
    private long PRAEFID;
    private int wunschStunden;
    private String modulauswahl;
    private String kommentar;
    private Einstellungtyp einstellungtyp;
    private  String einschraenkungen;
    private TutorenSchulungTeilnahme tutorenSchulungTeilnahme;

}
