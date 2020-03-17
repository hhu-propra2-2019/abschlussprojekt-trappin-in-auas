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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "personalien")
@Entity
@NoArgsConstructor
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

    public PersonalienDTO(AdresseDTO adresse, String unikennung, String name, String vorname,
        Date geburtsdatum, int alter, String geburtsort, String nationalitaet) {
        this.adresse = adresse;
        this.unikennung = unikennung;
        this.name = name;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.alter = alter;
        this.geburtsort = geburtsort;
        this.nationalitaet = nationalitaet;
    }
}
