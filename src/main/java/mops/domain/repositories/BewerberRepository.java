package mops.domain.repositories;

import java.util.List;


import mops.domain.database.dto.Bewerber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewerberRepository extends CrudRepository<Bewerber, String> {
    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAll();

    @Override
    <S extends Bewerber> S save(S entity);

    @Override
    <S extends Bewerber> Iterable<S> saveAll(Iterable<S> entities);
}
