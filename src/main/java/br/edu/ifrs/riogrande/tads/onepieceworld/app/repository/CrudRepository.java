package br.edu.ifrs.riogrande.tads.onepieceworld.app.repository;

import java.util.*;

import org.springframework.data.repository.*;

@NoRepositoryBean
public interface CrudRepository<T> extends Repository<T, UUID> {

  T save(T o);

  List<T> findAll();

  Optional<T> findById(UUID id);

  void delete(T o);

}
