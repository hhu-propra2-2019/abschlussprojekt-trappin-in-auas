package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class Modul {
    private String modul;

    public Modul(String modul) {
        this.modul = modul;
    }
}
