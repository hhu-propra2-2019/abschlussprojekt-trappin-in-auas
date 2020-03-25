package mops.services;

import mops.domain.database.dto.ZyklusDirigentDTO;
import mops.domain.repositories.ZyklusDirigentRepository;
import mops.domain.services.IZyklusDirigentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ZyklusDirigentService implements IZyklusDirigentService {

  @Autowired
  private ZyklusDirigentRepository repo;

  @Override
  public boolean getVerteilerPhase() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    return zyklusDirigentDTO.isVerteilerZeitraumAktiv();
  }

  @Override
  public boolean getBewerbungsPhase() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    return zyklusDirigentDTO.isBewerbungsZeitraumAktiv();
  }

  @Override
  public boolean getDozentenPhase() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    return zyklusDirigentDTO.isDozentenZeitraumAktiv();
  }

  @Override
  public void bewerbungsPhaseBeginnen() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    zyklusDirigentDTO.setBewerbungsZeitraumAktiv(true);
    zyklusDirigentDTO.setDozentenZeitraumAktiv(false);
    zyklusDirigentDTO.setVerteilerZeitraumAktiv(false);
    repo.save(zyklusDirigentDTO);
  }

  @Override
  public void dozentBewertungsphaseBeginnen() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    zyklusDirigentDTO.setBewerbungsZeitraumAktiv(false);
    zyklusDirigentDTO.setDozentenZeitraumAktiv(true);
    zyklusDirigentDTO.setVerteilerZeitraumAktiv(false);
    repo.save(zyklusDirigentDTO);
  }

  @Override
  public void verteilungsPhaseBeginnen() {
    erzeugeDirigent();
    ZyklusDirigentDTO zyklusDirigentDTO = repo.findAll().get(0);
    zyklusDirigentDTO.setBewerbungsZeitraumAktiv(false);
    zyklusDirigentDTO.setDozentenZeitraumAktiv(false);
    zyklusDirigentDTO.setVerteilerZeitraumAktiv(true);
    repo.save(zyklusDirigentDTO);
  }

  @Override
  public void erzeugeDirigent() {
    if (repo.count() == 0) {
      ZyklusDirigentDTO zyklusDirigentDTO = new ZyklusDirigentDTO();
      repo.save(zyklusDirigentDTO);
    } else {
      System.out.println("Es existiert bereits ein Dirigent in der Datenbank!");
      //throw new Exception("Es ist bereits ein Objekt in der Datenbank vorhanden!!!eins11!");
    }
  }
}
