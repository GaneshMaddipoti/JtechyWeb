package server.persistance.repository;

import server.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByUserName(String userName);

}
