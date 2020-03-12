package mops.domain.repositories;

import java.util.List;


import mops.domain.database.dto.BewerberDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewerberRepository extends CrudRepository<BewerberDTO, String> {
    BewerberDTO findBewerberByKennung(String kennung);
    List<BewerberDTO> findAll();

    @Override
    <S extends BewerberDTO> S save(S entity);

    @Override
    <S extends BewerberDTO> Iterable<S> saveAll(Iterable<S> entities);

    List<BewerberDTO> findByVerteiltAnIsNull();
    List<BewerberDTO> findByVerteiltAnIsNotNull();
}
