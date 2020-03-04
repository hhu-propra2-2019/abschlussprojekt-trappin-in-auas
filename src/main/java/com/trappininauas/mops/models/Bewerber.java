package com.trappininauas.mops.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bewerber{
    @Id
    private String kennung;
    private String nachname;
    private String vorname;
    private String geburtsdatum;
}