package mops.domain.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmartikulationsStatus {
    private boolean status;
    private String fachrichtung;
}
