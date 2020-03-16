package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Karriere {

    private String arbeitserfahrung;
    private ImmartikulationsStatus immartikulationsStatus;
    private StudiengangAbschluss fachAbschluss;

    
}
