package mops.domain.database.dto;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "personalien")
@Entity
public class PersonalienDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Embedded
    private AdresseDTO adresse;
    
    private String unikennung;
    private String name;
    private String vorname;

    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;

    private int alter;
    private String geburtsort;
    private String nationalitaet;

}
