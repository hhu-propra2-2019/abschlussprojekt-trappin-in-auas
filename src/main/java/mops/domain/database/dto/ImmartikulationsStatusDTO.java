package mops.domain.database.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Getter
@Setter
public class ImmartikulationsStatusDTO {
    private boolean status;
    private String fachrichtung;
}
