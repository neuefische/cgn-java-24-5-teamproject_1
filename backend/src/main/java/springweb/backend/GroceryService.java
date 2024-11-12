package springweb.backend;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {
    GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }


    public List<GroceryProduct> getAllGroceryProducts() {
        return groceryRepository.findAll();
    }
}
