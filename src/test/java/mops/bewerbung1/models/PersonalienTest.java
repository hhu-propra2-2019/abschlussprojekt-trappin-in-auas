package mops.bewerbung1.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mops.bewerbung1.testutils.ModelGenerator;
import mops.domain.models.Personalien;

@SpringBootTest
public class PersonalienTest {

  private transient ModelGenerator modelGenerator;

  @BeforeEach
  public void setUp() {
    this.modelGenerator = new ModelGenerator();
  }

  @Test
  public void personalienAlterTest() {
    Personalien p = modelGenerator.generatePersonalien();
    String geburtsdatumString = "29/01/1999";
    try {
      Date geburtsdatum = new SimpleDateFormat("dd/MM/yyyy").parse(geburtsdatumString);
      p.setGeburtsdatum(geburtsdatum);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
    assertEquals(21, p.getAlter());
  }
}