package mk.ukim.finki.emt.product.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ProductId extends DomainObjectId {
    private ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProductId of(String uuid) {
        ProductId p = new ProductId(uuid);
        return p;
    }


}
