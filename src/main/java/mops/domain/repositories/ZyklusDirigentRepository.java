package mops.domain.repositories;

import mops.domain.database.dto.ZyklusDirigentDTO;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ZyklusDirigentRepository extends CrudRepository<ZyklusDirigentDTO, Long> {
  List<ZyklusDirigentDTO> findAll();

  @Override
  <S extends ZyklusDirigentDTO> S save(S entity);

  @Override
  long count();
}
