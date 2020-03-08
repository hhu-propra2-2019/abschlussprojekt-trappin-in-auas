package mops.domain.database.models;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ImmartikulationsStatus {
    private boolean status;
    private String fachrichtung;

}
