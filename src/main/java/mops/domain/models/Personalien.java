package mops.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;

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

  @Min(14)
  private int alter;

  @NotBlank
  private String geburtsort;

  @NotBlank
  private String nationalitaet;




}
