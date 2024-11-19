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


    public GroceryProduct updateProduct(String id, GroceryProduct productDto) {
        if (groceryRepository.existsById(id)) {
            GroceryProduct updatedProduct = new GroceryProduct(id, productDto.category(),productDto.name(),productDto.price(),productDto.count(),productDto.status(),productDto.image());
            return groceryRepository.save(updatedProduct);
        } else {
            throw new NoSuchElementException("No Product found with Id:" + id);
        }
    }
}
