package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personalien {
    
    private Adresse adresse;
    private String unikennung;
    private String name;
    private String vorname;
    private Date geburtsdatum;
    private int alter;
    private String geburtsort;
    private String nationalitaet;

}
