package coffeeshop.models;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder(toBuilder = true)
@NoArgsConstructor @AllArgsConstructor
public class Customer {
    private String email;
    private String  password;
    private String firstname;
    private String lastname;
    private Address shippingAddress;
    private PaymentMode paymentMode;
    private Card cardDetails;
    private Address billingAddress;

    public Customer init(){
        Faker faker = new Faker();
        return this.toBuilder()
                .email(faker.internet().emailAddress())
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .password(faker.random().hex())
                .shippingAddress(new Address().init())
                .cardDetails(new Card().init())
                .build();

    }

}
