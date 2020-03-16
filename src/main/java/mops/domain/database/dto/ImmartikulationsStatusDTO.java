package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Embeddable
@Getter
@Setter
public class ImmartikulationsStatusDTO {
    private boolean status;
    private String fachrichtung;

    public ImmartikulationsStatusDTO(boolean status, String fachrichtung) {
        this.status = status;
        this.fachrichtung = fachrichtung;
    }
}
