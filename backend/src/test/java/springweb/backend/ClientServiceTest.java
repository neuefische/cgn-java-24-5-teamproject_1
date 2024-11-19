package springweb.backend;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private final ClientRepository mockUserRepo = mock(ClientRepository.class);

    @Test
    void GetAllClients_Test() {
        // GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "test-status", "apple.jpg");
        List<GroceryProduct> shoppingList1 = List.of(product);
        Client expectedClient1 = new Client("123", shoppingList1);

        GroceryProduct product2 = new GroceryProduct("2", "Fruit", "Banana", 500, 1, "test-status", "banane.jpg");
        GroceryProduct product3 = new GroceryProduct("3", "Fruit", "Kiwi", 1.80, 1, "test-status", "ps5.jpg");
        List<GroceryProduct> shoppingList2 = List.of(product2, product3);
        Client expectedClient2 = new Client("456", shoppingList2);

        List<Client> clientList = List.of(expectedClient1, expectedClient2);

        when(mockUserRepo.findAll()).thenReturn(clientList);
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        List<Client> actual = clientService.getAllClients();

        // THEN
        verify(mockUserRepo).findAll();
        assertEquals(clientList, actual);
    }

    @Test
    void GetClientById_Test() {
        // GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "test-status", "apple.jpg");
        List<GroceryProduct> shoppingList = List.of(product);
        Client expectedClient = new Client("123", shoppingList);

        when(mockUserRepo.findById("123")).thenReturn(Optional.of(expectedClient));
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        Optional<Client> result = clientService.getClientById("123");

        // THEN
        verify(mockUserRepo).findById("123");
        assertTrue(result.isPresent());
        assertEquals(expectedClient, result.get());
    }

    @Test
    void addGroceryProductToClient_Test() {
        // GIVEN
        GroceryProduct product1 = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "test-status", "apple.jpg");
        List<GroceryProduct> oldShoppingList = new ArrayList<>();
        oldShoppingList.add(product1);
        Client oldClient = new Client("123", oldShoppingList);

        GroceryProduct product2 = new GroceryProduct("2", "Fruit", "Banana", 500, 1, "test-status", "banana.jpg");
        List<GroceryProduct> newShoppingList = new ArrayList<>(oldShoppingList);
        newShoppingList.add(product2);
        Client newClient = new Client("123", newShoppingList);

        when(mockUserRepo.existsById("123")).thenReturn(true);
        when(mockUserRepo.findById("123")).thenReturn(Optional.of(oldClient));
        when(mockUserRepo.save(oldClient)).thenReturn(newClient);
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        Client actual = clientService.addGroceryProductToClient(product2, "123");

        // THEN
        verify(mockUserRepo).existsById("123");
        verify(mockUserRepo).findById("123");
        verify(mockUserRepo).save(oldClient);

        assertEquals(2, actual.shoppingList().size());
        assertEquals(product2, actual.shoppingList().get(1));
    }

    @Test
    void addClient_Test() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void updateClient_Test() {
        // GIVEN
        GroceryProduct product2 = new GroceryProduct("2", "Fruit", "Banana", 500, 1, "test-status", "banana.jpg");
        List<GroceryProduct> newShoppingList = List.of(product2);
        Client clientDto = new Client("123", newShoppingList);
        Client updatedClient = new Client("123", newShoppingList);

        when(mockUserRepo.existsById("123")).thenReturn(true);
        when(mockUserRepo.save(updatedClient)).thenReturn(updatedClient);
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        Client result = clientService.updateClient("123", clientDto);

        // THEN
        verify(mockUserRepo).existsById("123");
        verify(mockUserRepo).save(updatedClient);
        assertEquals(updatedClient, result);
        assertEquals("123", result.id());
        assertEquals(newShoppingList, result.shoppingList());
    }

    @Test
    void deleteClientById_Test() {
        // GIVEN
        String clientId = "123";

        when(mockUserRepo.existsById(clientId)).thenReturn(true);
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        boolean result = clientService.deleteClientById(clientId);

        // THEN
        verify(mockUserRepo).existsById(clientId);
        verify(mockUserRepo).deleteById(clientId);
        assertTrue(result);
    }

    @Test
    void deleteProductByIdFromClientByIdClient_Test() {
        // GIVEN
        String clientId = "123";
        String productIdToDelete = "2";

        GroceryProduct product1 = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "test-status", "apple.jpg");
        GroceryProduct product2 = new GroceryProduct("2", "Fruit", "Banana", 500, 1, "test-status", "banana.jpg");
        List<GroceryProduct> shoppingList = new ArrayList<>(List.of(product1, product2));
        Client client = new Client(clientId, shoppingList);

        when(mockUserRepo.existsById(clientId)).thenReturn(true);
        when(mockUserRepo.findById(clientId)).thenReturn(Optional.of(client));
        ClientService clientService = new ClientService(mockUserRepo);

        // WHEN
        clientService.deleteProductByIdFromClientById(clientId, productIdToDelete);

        // THEN
        assertEquals(1, client.shoppingList().size());
        assertEquals("1", client.shoppingList().get(0).id()); // Only product1 should remain
        verify(mockUserRepo).existsById(clientId);
        verify(mockUserRepo).findById(clientId);
        verify(mockUserRepo).save(client);
    }

    @Test
    void changeProductByIdFromClientById_Test() {
        //GIVEN

        //WHEN

        //THEN
    }
}