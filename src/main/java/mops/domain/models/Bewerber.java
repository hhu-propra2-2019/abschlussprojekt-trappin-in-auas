package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bewerber{

    private Karriere karriere;
    private Personalien personalien;
    private Praeferenzen praeferenzen;
    
    private String erstelltVon;
    private Dozent verteiltAn;
}
