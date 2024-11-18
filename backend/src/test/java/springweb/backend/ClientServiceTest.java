package springweb.backend;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private final ClientRepository mockUserRepo = mock(ClientRepository.class);

    @Test
    public void GetAllClients_Test() {
        // GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, "apple.jpg");
        List<GroceryProduct> shoppingList1 = List.of(product);
        Client expectedClient1 = new Client("123", shoppingList1);

        GroceryProduct product2 = new GroceryProduct("2", "Fruit", "Banana", 500, "banane.jpg");
        GroceryProduct product3 = new GroceryProduct("3", "Fruit", "Kiwi", 1.80, "ps5.jpg");
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
    public void GetClientById_Test() {
        // GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, "apple.jpg");
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
}