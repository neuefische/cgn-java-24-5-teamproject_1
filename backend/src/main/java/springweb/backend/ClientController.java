package springweb.backend;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/store/clients")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService groceryService) {
        this.clientService = groceryService;
    }

    @GetMapping
    public List<Client> getAllGroceryProducts() {
        return clientService.getAllClients();
    }



    /*
    @PostMapping
    public GroceryProduct addGroceryProduct(@RequestBody GroceryProduct groceryProduct) {
        return clientService.addGroceryProduct(groceryProduct);
    }
    * */

}
