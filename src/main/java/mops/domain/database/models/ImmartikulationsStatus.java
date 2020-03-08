package mops.database.models;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ImmartikulationsStatus {
    boolean status;
    String studiengang;

}
