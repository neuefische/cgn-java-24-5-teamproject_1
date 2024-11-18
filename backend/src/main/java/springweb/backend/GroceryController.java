package springweb.backend;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/store/products")
public class GroceryController {

    GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping
    public List<GroceryProduct> getAllGroceryProducts() {
        return groceryService.getAllGroceryProducts();
    }


}
