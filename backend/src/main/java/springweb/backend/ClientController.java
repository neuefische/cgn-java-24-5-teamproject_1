package springweb.backend;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/store/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping({"{idClient}"})
    public Client getClientByID(@PathVariable String idClient) {
        return clientService.getClientById(idClient).orElseThrow(() -> new NoSuchElementException("Task not found"));
    }

    @PostMapping("{idClient}/shoppingList")
    public Client addGroceryProductToClient(@PathVariable String idClient, @RequestBody GroceryProduct groceryProduct) {
        return clientService.addGroceryProductToClient(groceryProduct, idClient);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/{idClient}")
    public Client updateTask(@PathVariable String idClient, @RequestBody Client clientDto) {
        return clientService.updateClient(idClient, clientDto);
    }
    @PutMapping("{idClient}/shoppingList/{idProduct}")
    public Client changeProductCountByIdFromClientById(@PathVariable String idClient, @PathVariable String idProduct, @RequestBody GroceryProduct productDto) {
        return clientService.changeProductByIdFromClientById(idClient,idProduct,productDto);
    }

    @DeleteMapping({"{idClient}"})
    public void deleteClientById(@PathVariable String idClient) {
        clientService.deleteClientById(idClient);
    }

    @DeleteMapping("{idClient}/shoppingList/{idProduct}")
    public void deleteProductByIdFromClientById(@PathVariable String idClient, @PathVariable String idProduct) {
        clientService.deleteProductByIdFromClientById(idClient,idProduct);
    }
}
