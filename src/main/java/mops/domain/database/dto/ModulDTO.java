package mops.domain.database.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "module")
@NoArgsConstructor
public class ModulDTO {

    public ModulDTO(String modulName,  String dozentName,String dozentMail){
        this.modulName = modulName;
        this.dozentName = dozentName;
        this.dozentMail = dozentMail;

    }

    @GeneratedValue
    @Id
    private long id;
    private String modulName;
    private String dozentMail;
    private String dozentName;
}
