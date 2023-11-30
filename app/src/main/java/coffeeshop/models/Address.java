package coffeeshop.models;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Address {
   // private String firstname;
    //private String lastname;
    //private String doorNo;
    private String address;
    private String city;
    private String  state;
    private String country;
    private String apartment;
    private String postcode;
    public Address init() {
        return this.toBuilder()
                .country("Australia")
                .address("Sydney Opera House")
                .apartment("Bennelong Point")
                .city("Sydney")
                .state("New South Wales")
                .postcode("2000")
                .build();
    }


}
