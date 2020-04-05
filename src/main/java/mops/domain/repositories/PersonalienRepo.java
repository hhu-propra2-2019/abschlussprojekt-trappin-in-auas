package mops.domain.repositories;


import org.springframework.data.repository.CrudRepository;

import mops.domain.database.dto.PersonalienDTO;

import java.util.Optional;


public interface PersonalienRepo  extends CrudRepository<PersonalienDTO, Long> {


  @Override
  <S extends PersonalienDTO> S save(S entity);

  @Override
  <S extends PersonalienDTO> Iterable<S> saveAll(Iterable<S> entities);


  @Override
  Optional<PersonalienDTO> findById(Long id);



  @Override
  void deleteById(Long id);



}