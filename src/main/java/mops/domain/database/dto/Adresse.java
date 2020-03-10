package mops.domain.database.dto;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Adresse {
    private String PLZ;
    private String wohnort;
    private String wohnstadt;
    private String straße;
    private String hausnummer;
}