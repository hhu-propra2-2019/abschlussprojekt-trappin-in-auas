package mops.domain.repositories;

import mops.domain.database.dto.PraeferenzenDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PraeferenzenRepo  extends CrudRepository<PraeferenzenDTO,Long> {
  @Override
  <S extends PraeferenzenDTO> S save(S entity);

  @Override
  <S extends PraeferenzenDTO> Iterable<S> saveAll(Iterable<S> entities);


  @Override
  public void deleteAll();

  @Override
  Optional<PraeferenzenDTO> findById(Long id);


  @Override
  void deleteById(Long id);




}




