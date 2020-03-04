package mops.repositories;

import java.util.List;

import mops.models.Bewerber;
import org.springframework.data.repository.CrudRepository;

public interface BewerberRepository extends CrudRepository<Bewerber, String> {
    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAll();

    @Override
    <S extends Bewerber> S save(S entity);

    @Override
    <S extends Bewerber> Iterable<S> saveAll(Iterable<S> entities);
}
