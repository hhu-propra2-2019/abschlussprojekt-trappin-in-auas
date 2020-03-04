package mops.repositories;

import java.util.List;

import mops.models.Bewerber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewerberRepository extends CrudRepository<Bewerber, String> {
    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAll();
    Bewerber save(Bewerber b);
    void saveAll(List<Bewerber> b);
}
