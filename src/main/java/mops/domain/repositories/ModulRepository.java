package mops.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;
import mops.domain.database.dto.ModulDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulRepository extends CrudRepository<ModulDTO, Long> {

  ModulDTO findModulById(Long id);

  List<ModulDTO> findAll();

  @Query("select m from ModulDTO m where m.modulName=?1")
  ModulDTO findModulByModulName(String ModulName);

  @Override
  <S extends ModulDTO> S save(S entity);

  @Override
  <S extends ModulDTO> Iterable<S> saveAll(Iterable<S> entities);

  @Transactional
  @Modifying
  @Query("delete from ModulDTO m  where m.modulName  = ?1")
  void deleteModulByName(String modulName);
}
