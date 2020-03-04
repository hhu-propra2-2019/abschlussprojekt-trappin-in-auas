package com.trappininauas.trap.repositories;

import com.trappininauas.trap.models.Bewerber;
import org.springframework.data.repository.CrudRepository;

public interface BewerberRepository extends CrudRepository {
    Bewerber findBewerberByKennung(String kennung);
}
