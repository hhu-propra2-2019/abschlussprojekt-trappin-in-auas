package mops.services;

import java.util.List;
import mops.domain.models.lehrstuhl.Dozent;
import mops.domain.repositories.DozentRepository;
import mops.domain.services.IDozentService;
import org.springframework.stereotype.Service;

@Service
public class DozentService implements IDozentService {

  private transient DozentRepository dozentRepository;

  public DozentService(DozentRepository dozentRepository) {
    this.dozentRepository = dozentRepository;
  }

  public void add(Dozent dozent) {
    dozentRepository.save(dozent);
  }


  public List<Dozent> findAll() {
    return dozentRepository.findAll();
  }

}
