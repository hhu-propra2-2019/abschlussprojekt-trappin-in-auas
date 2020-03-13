package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "module")
public class ModulDTO {
    @GeneratedValue
    @Id
    private long id;
    private String modul;
    private String dozent;
    private String dozentName;
}
