package beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Company {
    private final int id;
    private final String name;
    private String email;
    private String password;
    private List<Coupon> coupons;


}
