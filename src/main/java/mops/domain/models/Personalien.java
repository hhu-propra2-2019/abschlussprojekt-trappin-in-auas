package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personalien {

  private Adresse adresse;
  private String unikennung;
  private String name;
  private String vorname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date geburtsdatum;

  private int alter;
  private String geburtsort;
  private String nationalitaet;

}
