package mops.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mops.domain.database.dto.DozentPraeferenzDTO;

public interface DozentPraeferenzRepository extends CrudRepository<DozentPraeferenzDTO, Long>{
  @Override
  <S extends DozentPraeferenzDTO> S save(S entity);

  @Override
  List<DozentPraeferenzDTO> findAll();

}