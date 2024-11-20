package springweb.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    void setup() {
        clientRepository.deleteAll();
    }

    @Test
    void getAllClients_Test() throws Exception {
        //GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "sold", "apple.jpg");
        List<GroceryProduct> shoppingList = List.of(product);
        Client testClient = new Client("123", shoppingList);
        clientRepository.save(testClient);

        //WHEN
        mockMvc.perform(get("/api/store/clients"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "123",
                                "shoppingList": [
                                {
                                  "id": "1",
                                  "category": "Fruit",
                                  "name": "Apple",
                                  "price": 0.99,
                                  "count": 1,
                                  "status": "sold",
                                  "image": "apple.jpg"
                                }
                                ]
                            }
                        ]
                        """));
    }

    @Test
    void getClientById_Test() throws Exception {
        //GIVEN
        GroceryProduct product = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "sold", "apple.jpg");
        List<GroceryProduct> shoppingList = List.of(product);
        Client testClient = new Client("123", shoppingList);
        clientRepository.save(testClient);

        //WHEN
        mockMvc.perform(get("/api/store/clients/123"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "123",
                            "shoppingList": [
                            {
                              "id": "1",
                              "category": "Fruit",
                              "name": "Apple",
                              "price": 0.99,
                              "count": 1,
                              "status": "sold",
                              "image": "apple.jpg"
                            }
                            ]
                        }
                        """));
    }

    @Test
    void addGroceryProductToClient_Test() throws Exception {
        //GIVEN
        GroceryProduct product1 = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "sold", "apple.jpg");
        List<GroceryProduct> oldShoppingList = List.of(product1);
        Client client = new Client("123", oldShoppingList);
        clientRepository.save(client);

        //WHEN
        mockMvc.perform(post("/api/store/clients/123/shoppingList")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id": "2",
                          "category": "Fruit",
                          "name": "Banana",
                          "price": 500,
                          "count": 1,
                          "status": "test-status",
                          "image": "banane.jpg"
                        }
                        """))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "123",
                            "shoppingList": [
                            {
                              "id": "1",
                              "category": "Fruit",
                              "name": "Apple",
                              "price": 0.99,
                              "count": 1,
                              "status": "sold",
                              "image": "apple.jpg"
                            },
                            {
                              "id": "2",
                              "category": "Fruit",
                              "name": "Banana",
                              "price": 500,
                              "count": 1,
                              "status": "test-status",
                              "image": "banane.jpg"
                            }
                            ]
                        }
                        """));
    }

    @Test
    void addClient_Test() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void updateClient_Test() throws Exception {
        //GIVEN
        GroceryProduct product1 = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "sold", "apple.jpg");
        List<GroceryProduct> oldShoppingList = List.of(product1);
        Client oldClient = new Client("123", oldShoppingList);
        clientRepository.save(oldClient);

        //WHEN
        mockMvc.perform(put("/api/store/clients/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": "123",
                            "shoppingList": [
                            {
                              "id": "3",
                              "category": "Fruit",
                              "name": "Mango",
                              "price": 0.99,
                              "count": 1,
                              "status": "sold",
                              "image": "mango.jpg"
                            },
                            {
                              "id": "2",
                              "category": "Fruit",
                              "name": "Banana",
                              "price": 500,
                              "count": 1,
                              "status": "test-status",
                              "image": "banane.jpg"
                            }
                            ]
                        }
                        """))

        //THEN
        .andExpect(status().isOk())
        .andExpect(content().json("""
                        {
                            "id": "123",
                            "shoppingList": [
                            {
                              "id": "3",
                              "category": "Fruit",
                              "name": "Mango",
                              "price": 0.99,
                              "count": 1,
                              "status": "sold",
                              "image": "mango.jpg"
                            },
                            {
                              "id": "2",
                              "category": "Fruit",
                              "name": "Banana",
                              "price": 500,
                              "count": 1,
                              "status": "test-status",
                              "image": "banane.jpg"
                            }
                            ]
                        }
                        """));
    }

    @Test
    void changeProductCountByIdFromClientById_Test() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void deleteClientById_Test() throws Exception {
        //GIVEN
        GroceryProduct product1 = new GroceryProduct("1", "Fruit", "Apple", 0.99, 1, "sold", "apple.jpg");
        List<GroceryProduct> oldShoppingList = List.of(product1);
        Client oldClient = new Client("123", oldShoppingList);
        clientRepository.save(oldClient);

        //WHEN
        mockMvc.perform(delete("/api/store/clients/123"))

        //THEN
        .andExpect(status().isOk());
        assertFalse(clientRepository.existsById("123"));
    }

    @Test
    void deleteProductByIdFromClientById_Test() {
        //GIVEN

        //WHEN

        //THEN
    }
}
