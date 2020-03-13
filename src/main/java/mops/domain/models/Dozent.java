package mops.domain.models;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Dozent {
    private String dozentMail;
    private String dozentName;
}