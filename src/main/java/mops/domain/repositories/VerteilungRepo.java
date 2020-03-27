package mops.domain.repositories;

import mops.domain.database.dto.VerteilungDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerteilungRepo extends CrudRepository<VerteilungDTO,Long> {

  int countVerteilungDTOByDozentKennungEquals(String dozentKennung);
}
