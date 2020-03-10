package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data

@Table(name = "personalien")
@Entity
public class PersonalienDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Adresse adresse;
    private String unikennung;
    private String name;
    private String vorname;
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    private int alter;
    private String geburtsort;
    private String nationalitaet;

}
