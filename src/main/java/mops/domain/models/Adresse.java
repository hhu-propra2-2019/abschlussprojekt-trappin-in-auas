package mops.domain.models;


import lombok.Data;

@Data
public class Adresse {
    private String PLZ;
    private String wohnort;
    private String straße;
    private String hausnummer;

    public Adresse(String PLZ, String wohnort, String straße, String hausnummer) {
        this.PLZ = PLZ;
        this.wohnort = wohnort;
        this.straße = straße;
        this.hausnummer = hausnummer;
    }
}