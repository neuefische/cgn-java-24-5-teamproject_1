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
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping({"id"})
    public List<GroceryProduct> getAllGroceryProductsFromClient(@PathVariable String id) {
        return clientService.getAllGroceryProductsFromClient(id);
    }

    @PostMapping("{id}/shoppingList")
    public Client addGroceryProductToClient(@PathVariable String id, @RequestBody GroceryProduct groceryProduct) {
        return clientService.addGroceryProductToClient(groceryProduct, id);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping({"id"})
    public void deleteClientById(@PathVariable String id) {
        clientService.deleteClientById(id);
    }

    @DeleteMapping("{idClient}/shoppingList{idProduct}")
    public void deleteProductByIdFromClientById(@PathVariable String idClient, @PathVariable String idProduct) {
        clientService.deleteProductByIdFromClientById(idClient,idProduct);
    }
}
