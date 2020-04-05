package mops.domain.repositories;

import mops.domain.database.dto.KarriereDTO;
import org.springframework.data.repository.CrudRepository;


public interface KarriereRepo  extends CrudRepository<KarriereDTO, Long> {


  @Override
  <S extends KarriereDTO> S save(S entity);

  @Override
  <S extends KarriereDTO> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  void deleteById(Long id);
}
