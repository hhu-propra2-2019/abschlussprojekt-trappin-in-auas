package mops.domain.repositories;

import java.util.List;
import javax.transaction.Transactional;
import mops.domain.database.dto.DozentPraeferenzDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface DozentPraeferenzRepo extends CrudRepository<DozentPraeferenzDTO, Long> {

  @Override
  <S extends DozentPraeferenzDTO> S save(S entity);

  @Query("select d FROM DozentPraeferenzDTO d where d.bewerber=?1 and d.dozentMail =?2")
  List<DozentPraeferenzDTO> findByBewerberAndDozentMail(String bewerber, String dozentMail);

  @Transactional
  @Modifying
  @Query("delete from DozentPraeferenzDTO d where d.bewerber=?1 and d.dozentMail=?2 ")
  void deleteByBewerberAndDozentMail(String bewerber, String dozentMail);
}
