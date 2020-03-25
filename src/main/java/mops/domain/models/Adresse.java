package mops.domain.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Adresse {
    private String PLZ;
    private String wohnort;
    private String strasse;
    private String hausnummer;

    public Adresse(String PLZ, String wohnOrt, String strasse, String hausnummer) {
        this.PLZ = PLZ;
        this.wohnort = wohnOrt;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }
}