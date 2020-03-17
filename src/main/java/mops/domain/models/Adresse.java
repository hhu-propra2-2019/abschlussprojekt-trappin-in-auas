package mops.domain.models;


import lombok.Data;

@Data
public class Adresse {
    private String PLZ;
    private String wohnort;
    private String strasse;
    private String hausnummer;

    public Adresse(String PLZ, String wohnort, String strasse, String hausnummer) {
        this.PLZ = PLZ;
        this.wohnort = wohnort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }
}