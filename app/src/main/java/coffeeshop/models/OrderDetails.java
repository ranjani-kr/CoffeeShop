package coffeeshop.models;
import lombok.Data;

@Data
public class OrderDetails {

    private long orderID;
    private Cart cartSummary;
    private  Customer customerInformation;

}
