package mops.domain.models;

import lombok.Data;


@Data
public class ModulAuswahl {
    private Modul modul;
    private int prioritaet;
    private Praeferenzen praeferenzen;
}
