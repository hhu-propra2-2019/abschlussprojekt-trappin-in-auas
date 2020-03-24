package mops.domain.repositories;

import java.util.List;

import javax.transaction.Transactional;
import mops.domain.database.dto.BewerberDTO;
import mops.domain.database.dto.VerteilungDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewerberRepository extends CrudRepository<BewerberDTO, String> {
    List<BewerberDTO> findAll();

    @Override
    <S extends BewerberDTO> S save(S entity);

    @Override
    <S extends BewerberDTO> Iterable<S> saveAll(Iterable<S> entities);

    @Query("select b from BewerberDTO b where b.kennung=?1")
    BewerberDTO findBewerberByKennung(String kenung);

    List<BewerberDTO> findBewerberDTOBByVerteiltAnIsNull();

    List<BewerberDTO> findByVerteiltAnIsNotNull();


}


