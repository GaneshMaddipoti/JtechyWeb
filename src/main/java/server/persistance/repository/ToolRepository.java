package server.persistance.repository;

import server.model.Tool;
import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<Tool, Integer> {

}
