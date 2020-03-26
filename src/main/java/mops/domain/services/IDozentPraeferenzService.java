package mops.domain.services;


import mops.domain.models.DozentPraeferenz;

public interface IDozentPraeferenzService {
  void addPraeferenz(DozentPraeferenz dozentPraeferenz);

  void deletePraeferenz(String bewerber, String dozentMail);

  Integer getDozentPraeferenz(String bewerber, String dozentMail);

  boolean alreadyConfirmed(String bewerber, String dozentMail);




}
