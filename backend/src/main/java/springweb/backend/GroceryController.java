package springweb.backend;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping(path="/{idProduct}")
    public GroceryProduct updateProduct(@PathVariable String idProduct, @RequestBody GroceryProduct productDto) {
        return groceryService.updateProduct(idProduct, productDto);
    }

}
