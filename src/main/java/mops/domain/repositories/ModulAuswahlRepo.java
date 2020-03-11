package mops.domain.repositories;

import mops.domain.database.dto.ModulAuswahlDTO;
import org.springframework.data.repository.CrudRepository;


public interface ModulAuswahlRepo extends CrudRepository<ModulAuswahlDTO, Long> {
}
