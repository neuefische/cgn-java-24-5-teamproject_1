package springweb.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroceryRepository extends MongoRepository<GroceryProduct,String> {
}
