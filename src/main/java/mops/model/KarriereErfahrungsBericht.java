package mops.model;


import lombok.Data;

import java.util.List;

@Data
public class KarriereErfahrungsBericht {
    private long KID;
    private String arbeitserfahrung;
    private ImmartikulationsStatus immartikulationsStatus;
    private String fachabschluss;
    private String abschlusstyp;
    private List<String> bestandendeModule;

}
