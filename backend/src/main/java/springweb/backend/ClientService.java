package springweb.backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id);
    }

    public Client addGroceryProductToClient(GroceryProduct groceryProduct, String id) {

        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            client.shoppingList().add(groceryProduct);
            return clientRepository.save(client);
        } else {
            throw new NoSuchElementException("No Client found with Id:" + id);
        }
    }

    public Client addClient(Client clientDto) {
        Client client = new Client(clientDto.id(), clientDto.shoppingList());
        return clientRepository.save(client);
    }

    public Client updateClient(String id, Client clientDto) {
        if (clientRepository.existsById(id)) {
            Client updatedClient = new Client(id, clientDto.shoppingList());
            return clientRepository.save(updatedClient);
        } else {
            throw new NoSuchElementException("No Client found with Id:" + id);
        }
    }

    public boolean deleteClientById(String id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            throw new NoSuchElementException("No Client found with Id:" + id);
        }
    }

    /* WIP
    public List<GroceryProduct> getAllGroceryProductsFromClient(String id) {
        if (clientRepository.existsById(id)) {
            return clientRepository.findById(id).get().shoppingList();
        }
        throw new NoSuchElementException("No Client found with Id:" + id);
        }
     */

    public void deleteProductByIdFromClientById(String idClient, String idProduct) {
        if (clientRepository.existsById(idClient)) {
            Client client = clientRepository.findById(idClient).get();
            boolean productExists = client.shoppingList()
                    .stream()
                    .anyMatch(product -> product.id().equals(idProduct));
            if (!client.shoppingList().isEmpty() && productExists) {

                List<GroceryProduct> filteredList = client.shoppingList()
                        .stream()
                        .filter(product -> !product.id().equals(idProduct))
                        .collect(Collectors.toList());

                client.shoppingList().clear();
                client.shoppingList().addAll(filteredList);

                clientRepository.save(client);
            } else {
                throw new NoSuchElementException("No Product found with Id:" + idProduct);
            }
        } else {
            throw new NoSuchElementException("No Client found with Id:" + idClient);
        }
    }


    public void changeProductByIdFromClientById(String idClient, String idProduct) {
    }
}
