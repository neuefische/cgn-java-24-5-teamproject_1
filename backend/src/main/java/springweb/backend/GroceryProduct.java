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
        GroceryProduct(
                String category,
                String name,
                double price,
                Integer count,
                String status,
                String image
        ){
                this(null, category, name,price,count,status,image);
        }

        public GroceryProduct withId(String id){
                return new GroceryProduct(id,category, name,price,count,status,image);
        }
}
