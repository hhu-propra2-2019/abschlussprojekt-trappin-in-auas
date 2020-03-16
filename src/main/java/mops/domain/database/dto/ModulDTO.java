package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "module")
public class ModulDTO {

    public ModulDTO(String modul, String dozentMail, String dozentName){
        this.modul = modul;
        this.dozentMail = dozentMail;
        this.dozentName = dozentName;
    }

    @GeneratedValue
    @Id
    private long id;
    private String modul;
    private String dozentMail;
    private String dozentName;
}
