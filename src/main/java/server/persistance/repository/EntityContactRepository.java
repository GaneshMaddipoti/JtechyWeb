package server.persistance.repository;


import server.model.EntityContact;
import org.springframework.data.repository.CrudRepository;

public interface EntityContactRepository extends CrudRepository<EntityContact, Integer> {

}
