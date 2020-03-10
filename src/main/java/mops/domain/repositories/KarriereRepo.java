package mops.domain.repositories;

import mops.domain.database.models.Bewerber;
import mops.domain.database.models.Karriere;
import mops.domain.database.models.Personalien;
import org.springframework.data.repository.CrudRepository;


public interface KarriereRepo  extends CrudRepository<Karriere, Long> {


    @Override
    <S extends Karriere> S save(S entity);

    @Override
    <S extends Karriere> Iterable<S> saveAll(Iterable<S> entities);



    @Override
    void deleteById(Long id);


}
