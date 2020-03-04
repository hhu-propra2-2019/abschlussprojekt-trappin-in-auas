package mops.repositories;

import java.util.List;

import mops.models.Bewerber;
import org.springframework.data.repository.CrudRepository;

public interface BewerberRepository extends CrudRepository<Bewerber, String> {
    Bewerber findBewerberByKennung(String kennung);
    List<Bewerber> findAll();
}
