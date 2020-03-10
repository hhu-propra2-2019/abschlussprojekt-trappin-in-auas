package mops.domain.repositories;

import mops.domain.database.models.Praeferenzen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PraeferenzenRepo  extends CrudRepository<Praeferenzen,Long> {
    @Override
    <S extends Praeferenzen> S save(S entity);

    @Override
    <S extends Praeferenzen> Iterable<S> saveAll(Iterable<S> entities);


    @Override
    public void deleteAll();

    @Override
    Optional<Praeferenzen> findById(Long id);


    @Override
    void deleteById(Long id);




    }




