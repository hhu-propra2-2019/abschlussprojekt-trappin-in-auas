
package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import mops.domain.models.Bewerber;
import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;

@Service
public class BewerberService implements IBewerberService {

    private BewerberRepository bewerberRepository;

    public BewerberService(BewerberRepository bewerberRepository) {
        this.bewerberRepository = bewerberRepository;
    }

    @Override
    public void addBewerber(Bewerber b) {
        bewerberRepository.save(b);
    }

    @Override
    public Bewerber findBewerberByKennung(String kennung) {
        return bewerberRepository.findById(kennung).get();
    }

    public List<Bewerber> findAlleBewerber(){
        return bewerberRepository.findAll();
    }

    @Override
    public List<Bewerber> findAlleNichtVerteilteBewerber() {
        return bewerberRepository.findAll().stream().filter(x -> x.getVerteiltAn() == null).collect(Collectors.toList());
    }
}