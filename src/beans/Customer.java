package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
public class Customer {
    // Fields
    private final int id;
    private final String firstName;
    private final String lastName;
    private String email;
    private String password;
    private List<Coupon> coupons;


}
