package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class Modul {
    private String modulName;
    private Dozent dozent;

    public Modul() {
        this.dozent = new Dozent();
    }
}
