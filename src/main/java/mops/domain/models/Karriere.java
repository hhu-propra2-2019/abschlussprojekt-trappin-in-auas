package mops.domain.models;

import lombok.Data;

import java.util.List;

@Data
public class Karriere {

    private long id;
    private String arbeitserfahrung;
    private ImmartikulationsStatus immartikulationsStatus;
    private StudiengangAbschluss fachAbschluss;
    private List<BestandeneModule> bestandendeModule;

}
