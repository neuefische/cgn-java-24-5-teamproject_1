package springweb.backend;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public record GroceryProduct(
        String id,
        String category,
        String name,
        double price,
        Integer count,
        String status,
        String image
        ) {
}
