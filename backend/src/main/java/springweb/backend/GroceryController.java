package springweb.backend;


import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{idProduct}")
    public GroceryProduct updateProduct(@PathVariable String id, @RequestBody GroceryProduct productDto) {
        return groceryService.updateProduct(id, productDto);
    }

}
