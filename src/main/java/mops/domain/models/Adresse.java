package mops.domain.models;


import lombok.Data;

@Data
public class Adresse {
    private String PLZ;
    private String wohnort;
    private String straße;
    private String hausnummer;
}