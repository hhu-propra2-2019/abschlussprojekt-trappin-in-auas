package mops.domain.models.fragebogen;

import lombok.Data;

@Data
public class Addresse {
    private int PLZ;
    private String wohnort;
    private String wohnstadt;
    private String straße;
    private String hausnummer;
}
