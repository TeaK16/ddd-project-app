package mk.ukim.finki.emt.product.domain.models;


import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.product.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "product")
@Getter
public class Product extends AbstractEntity<ProductId> {

    private String productName;

    private int sales;

    @AttributeOverrides({
      @AttributeOverride(name="amount", column = @Column(name = "price_amount")),
      @AttributeOverride(name="currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    private Product(){
        super(ProductId.randomId(ProductId.class));
    }
    public static  Product build(String productName, Money price, int sales){
        Product p = new Product();
        p.price = price;
        p.productName = productName;
        p.sales = sales;
        return p;
    }

    public void  addSales(int qty) {
        this.sales = this.sales - qty;
    }
    public void removeSales(int qty){
        this.sales -= qty;
    }
}
