package mops.domain.repositories;

import mops.domain.database.models.Karriere;
import mops.domain.database.models.Personalien;
import mops.domain.database.models.Praeferenzen;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PersonalienRepo  extends CrudRepository<Personalien, Long> {


    @Override
    <S extends Personalien> S save(S entity);

    @Override
    <S extends Personalien> Iterable<S> saveAll(Iterable<S> entities);


    @Override
    Optional<Personalien> findById(Long id);



    @Override
    void deleteById(Long id);



}