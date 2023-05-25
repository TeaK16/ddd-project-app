package mk.ukim.finki.emt.product.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Quantity {

    private final int quantity;

    protected Quantity(){
        this.quantity = 0;
    }


}
