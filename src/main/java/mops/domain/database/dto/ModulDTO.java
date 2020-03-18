package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "module")
@NoArgsConstructor
public class ModulDTO {

    public ModulDTO(String modulName, String dozentMail, String dozentName){
        this.modulName = modulName;
        this.dozentMail = dozentMail;
        this.dozentName = dozentName;
    }

    @GeneratedValue
    @Id
    private long id;
    private String modulName;
    private String dozentMail;
    private String dozentName;
}
