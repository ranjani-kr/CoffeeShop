package coffeeshop.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder(toBuilder = true)
@NoArgsConstructor @AllArgsConstructor
public class Product {
    private String name;
    private String searchKeyword;
    private String price;
    private String brand;
    private String size;
    private String colour;
    private String description;
    private String quantity;

    public Product init() {
        return this.toBuilder()
                    .searchKeyword("Jeans")
                .name("Belted Jeans")
                .colour("Sand Stone")
                .size("2")
                .quantity("1")
                .price("Rs. 299.60")
                .build();


    }

}
