package mops.domain.database.dto;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class ImmartikulationsStatusDTO {
    private boolean status;
    private String fachrichtung;

    public ImmartikulationsStatusDTO(boolean status, String fachrichtung) {
        this.status = status;
        this.fachrichtung = fachrichtung;
    }
}
