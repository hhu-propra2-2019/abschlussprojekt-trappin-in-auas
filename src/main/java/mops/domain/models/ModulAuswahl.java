package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ModulAuswahl {
    private Modul modul;
    private int prioritaet;
    private Praeferenzen praeferenzen;
}
