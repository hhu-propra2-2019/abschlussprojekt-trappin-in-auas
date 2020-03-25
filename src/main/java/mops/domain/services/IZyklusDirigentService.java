package mops.domain.services;

public interface IZyklusDirigentService {
  boolean getVerteilerPhase();

  boolean getBewerbungsPhase();

  boolean getDozentenPhase();

  public void bewerbungsPhaseBeginnen();

  public void dozentBewertungsphaseBeginnen();

  public void verteilungsPhaseBeginnen();

  public void erzeugeDirigent() throws Exception;
}
