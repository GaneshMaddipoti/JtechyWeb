package server.persistance.repository;

import server.model.EntityInfo;
import org.springframework.data.repository.CrudRepository;

public interface EntityInfoRepository extends CrudRepository<EntityInfo, Integer> {

}
