package server.strategy;


import server.model.AbstractEntity;
import java.util.List;
import java.util.Map;

public interface EntitiesByCategoryStrategy<T extends AbstractEntity> {

    Map<String, List<T>> entitiesByCategory(Iterable<T> entities);

}
