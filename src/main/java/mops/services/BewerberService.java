package mops.services;

import java.util.List;
import java.util.stream.Collectors;

import mops.domain.database.dto.BewerberDTO;
import org.springframework.stereotype.Service;


import mops.domain.repositories.BewerberRepository;
import mops.domain.services.IBewerberService;


@Service
public class BewerberService implements IBewerberService {

    private transient BewerberRepository bewerberRepository;

    public BewerberService(BewerberRepository bewerberRepository) {
        this.bewerberRepository = bewerberRepository;
    }

    @Override
    public void addBewerber(BewerberDTO b) {
        bewerberRepository.save(b);
    }

    @Override
    public BewerberDTO findBewerberByKennung(String kennung) {
        return bewerberRepository.findById(kennung).get();
    }

    public List<BewerberDTO> findAlleBewerber(){
        return bewerberRepository.findAll();
    }

    @Override
    public List<BewerberDTO> findAlleNichtVerteilteBewerber(List<BewerberDTO> alleBewerber) {
        return alleBewerber.stream().filter(x -> x.getVerteiltAn() == null).collect(Collectors.toList());
    }

    @Override
    public void verteile(String kennung, String dozent) {
        BewerberDTO b = bewerberRepository.findById(kennung).get();
        b.setVerteiltAn(dozent);
        bewerberRepository.save(b);
    }

	public List<BewerberDTO> findAlleVerteilteBewerber(List<BewerberDTO> alleBewerber) {
		return alleBewerber.stream().filter(x -> x.getVerteiltAn() != null).collect(Collectors.toList());
    }
    
    public List<BewerberDTO> findNichtVerteilt(){
        return bewerberRepository.findByVerteiltAnIsNull();
    }

    public List<BewerberDTO> findVerteilt(){
        return bewerberRepository.findByVerteiltAnIsNotNull();
    }
}