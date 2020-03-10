package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class StudiengangAbschluss {
    private String studiengang;
    private String abschluss;
}
