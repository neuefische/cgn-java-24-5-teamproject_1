package springweb.backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroceryService {
    GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }


    public List<GroceryProduct> getAllGroceryProducts() {
        return groceryRepository.findAll();
    }


    public GroceryProduct updateProduct(String id, GroceryProduct product) {
        if (groceryRepository.existsById(id)) {
            GroceryProduct updatedProduct = product.withId(product.id());
            return groceryRepository.save(updatedProduct);
        } else {
            throw new NoSuchElementException("No Product found with Id:" + id);
        }
    }
}
