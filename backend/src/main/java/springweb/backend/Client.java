package springweb.backend;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("clients")
public record Client(
        List<GroceryProduct> shoppingList
) {
}