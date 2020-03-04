package com.trappininauas.trap.bewerber1;

import lombok.Data;

import java.util.Date;
@Data
public class PersonalBackground {
    private long PID;
    private Addresse addresse;
    private String unikennung;
    private String name;
    private String vorname;
    private Date geburtstag;
    private int alter;
    private String geburtsort;
    private String national;

}
