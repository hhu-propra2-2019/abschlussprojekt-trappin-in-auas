package com.trappininauas.trap.bewerber1;


import lombok.Data;

@Data
public class Addresse {
    private long AID;
    private int PLZ;
    private String wohnort;
    private String wohnstadt;
    private String straße;
    private String hausnummer;
    private String bundesland;
}