package mops.domain.models;

import lombok.Data;

import java.util.Date;

@Data
public class Geburtsdaten {
    private Date geburtstag;
    private int alter;
    private String geburtsort;
    private String national;

}
