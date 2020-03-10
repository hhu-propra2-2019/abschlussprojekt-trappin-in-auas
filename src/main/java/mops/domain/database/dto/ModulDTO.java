package mops.domain.database.dto;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ModulDTO {
    private String modul;
}
