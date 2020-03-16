package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modul {
    private String modulName;
    private Dozent dozent;

    public Modul(String modulName) {
        this.modulName = modulName;
    }
}
