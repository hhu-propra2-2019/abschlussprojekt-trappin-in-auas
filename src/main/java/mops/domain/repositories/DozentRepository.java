package mops.domain.repositories;

import java.util.List;
import mops.domain.models.lehrstuhl.Dozent;
import org.springframework.data.repository.CrudRepository;

public interface DozentRepository extends CrudRepository<Dozent, Long> {

  List<Dozent> findAll();

  @Override
  <S extends Dozent> S save(S entity);

}
