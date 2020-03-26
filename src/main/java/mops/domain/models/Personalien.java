package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personalien {

  @Valid
  private Adresse adresse;

  @NotBlank
  private String name;

  @NotBlank
  private String vorname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent
  private Date geburtsdatum;

  public void setGeburtsdatum(Date geburtsdatum){
    this.geburtsdatum = geburtsdatum;

    LocalDate today = LocalDate.now();
    LocalDate birthday = geburtsdatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
    this.alter = Period.between(birthday, today).getYears();
  }

  private int alter;

  @NotBlank
  private String geburtsort;

  @NotBlank
  private String nationalitaet;
}
