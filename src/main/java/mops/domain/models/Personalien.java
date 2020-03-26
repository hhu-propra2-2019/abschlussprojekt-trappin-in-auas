package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personalien {

  private Adresse adresse;
  private String name;
  private String vorname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date geburtsdatum;

  private int alter;
  private String geburtsort;
  private String nationalitaet;




}
