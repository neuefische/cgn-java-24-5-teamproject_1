package springweb.backend;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("clients")
public record Client(
        String id,
        List<GroceryProduct> shoppingList
) {
}
