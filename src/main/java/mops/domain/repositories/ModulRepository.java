package mops.domain.repositories;

import java.util.List;

import mops.domain.database.dto.ModulDTO;
import org.springframework.data.repository.CrudRepository;

public interface ModulRepository extends CrudRepository<ModulDTO, Long> {

  ModulDTO findModulById(Long id);

  List<ModulDTO> findAll();

  @Override
  <S extends ModulDTO> S save(S entity);

  @Override
  <S extends ModulDTO> Iterable<S> saveAll(Iterable<S> entities);
}
