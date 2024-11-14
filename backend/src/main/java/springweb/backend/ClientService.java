package springweb.backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }



    public Client addGroceryProductToClient(GroceryProduct groceryProduct,String id) {

        if (clientRepository.existsById(id)){
            GroceryProduct newGroceryProduct = new GroceryProduct(
                    groceryProduct.id(),
                    groceryProduct.category(),
                    groceryProduct.name(),
                    groceryProduct.price(),
                    groceryProduct.image());

            Client client = clientRepository.findById(id).get();
            client.shoppingList().add(newGroceryProduct);
            return clientRepository.save(client);
        }else {
            throw new NoSuchElementException("No Client found with Id:" + id);
        }
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public boolean deleteClientById(String id) {
        if (clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            return true;
        }else {
            throw new NoSuchElementException("No Client found with Id:" + id);
        }
    }

    public List<GroceryProduct> getAllGroceryProductsFromClient(String id) {
        if (clientRepository.existsById(id)){
            return clientRepository.findById(id).get().shoppingList();
        }
        throw new NoSuchElementException("No Client found with Id:" + id);
    }

    public void deleteProductByIdFromClientById(String idClient, String idProduct) {
        if (clientRepository.existsById(idClient)){
            Client client = clientRepository.findById(idClient).get();
            boolean productExists = client.shoppingList()
                    .stream()
                    .anyMatch(product -> product.id().equals(idProduct));
            if (!client.shoppingList().isEmpty() && productExists){

                List<GroceryProduct> filteredList = client.shoppingList()
                        .stream()
                        .filter(product -> !product.id().equals(idProduct))
                        .collect(Collectors.toList());

                client.shoppingList().clear();
                client.shoppingList().addAll(filteredList);

                clientRepository.save(client);
            }
            throw new NoSuchElementException("No Product found with Id:" + idProduct);

        }
        throw new NoSuchElementException("No Client found with Id:" + idClient);
    }
}
