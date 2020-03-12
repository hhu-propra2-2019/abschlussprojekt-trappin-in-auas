package mops.domain.models;

import lombok.Data;

@Data

public class Modul {
    private String modulName;

    public Modul(String modulName) {
        this.modulName = modulName;
    }
}
