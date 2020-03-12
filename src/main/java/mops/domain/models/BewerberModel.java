package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BewerberModel{

    public BewerberModel(String kennung, String nachname, String vorname, String geburtsdatum){
        this.kennung = kennung;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
    }
    
    private String kennung;
    private String nachname;
    private String vorname;
    private String geburtsdatum;
    private String verteiltAn;
}
