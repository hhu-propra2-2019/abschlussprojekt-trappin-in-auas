package mops.domain.repositories;

import mops.domain.database.dto.DozentPraeferenzDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DozentPraeferenzRepo extends CrudRepository<DozentPraeferenzDTO, Long> {

  @Override
  <S extends DozentPraeferenzDTO> S save(S entity);
}
