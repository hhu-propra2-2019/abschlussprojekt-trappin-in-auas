package mops.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;
import mops.domain.database.dto.ModulDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ModulRepository extends CrudRepository<ModulDTO, Long> {

  ModulDTO findModulById(Long id);

  List<ModulDTO> findAll();

  @Override
  <S extends ModulDTO> S save(S entity);

  @Override
  <S extends ModulDTO> Iterable<S> saveAll(Iterable<S> entities);

  @Transactional
  @Modifying
  @Query("delete from ModulDTO m  where m.modul  = ?1")
  void deleteModulByName(String modulName);
}
