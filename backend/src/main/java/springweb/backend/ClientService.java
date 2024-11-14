package springweb.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }



 /*
    public GroceryProduct addGroceryProduct(GroceryProduct groceryProduct) {

        return clientRepository.save(groceryProduct);
    }
 * */
}
