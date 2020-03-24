package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Modul {
    private String modulName;
    private Dozent dozent;

    public Modul() {
        this.dozent = new Dozent();
    }
}
