package mops.domain.repositories;

import java.util.List;
import mops.domain.models.lehrstuhl.Modul;
import org.springframework.data.repository.CrudRepository;

public interface ModulRepository extends CrudRepository<Modul, Long> {
  Modul findModulById(Long id);
  List<Modul> findAll();

  @Override
  <S extends Modul> S save(S entity);

  @Override
  <S extends Modul> Iterable<S> saveAll(Iterable<S> entities);
}
