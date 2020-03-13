package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class ModulDTO {
    private String modul;
}
