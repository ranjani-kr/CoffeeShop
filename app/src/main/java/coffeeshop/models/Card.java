package coffeeshop.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String name;
    private Expiry expiryDate;
    private String number;
    private int securityCode;
    public Card init() {
        return this.toBuilder()
                .name("Test User")
                .number("1")
                .expiryDate(new Expiry().init())
                .securityCode(111)
                .build();
    }

    @Data @Builder(toBuilder = true)
    @NoArgsConstructor @AllArgsConstructor
    public static class Expiry {
        private int month;
        private int year;

        public Expiry init()

        {
            return this.toBuilder()
                    .month(02)
                    .year(28)
                    .build();
        }

        @Override
        public String toString() {
            return "0"+month+year;
        }
    }
}
