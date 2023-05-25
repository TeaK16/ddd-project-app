package mk.ukim.finki.emt.product.domain.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.product.domain.models.Product;
import mk.ukim.finki.emt.product.domain.repository.ProductRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
        Product p1 = Product.build("Pie", Money.valueOf(Currency.MKD,500),10);
        Product p2 = Product.build("Cake",Money.valueOf(Currency.EUR,3),10);
        if(productRepository.findAll().isEmpty()){
            productRepository.saveAll(Arrays.asList(p1,p2));
        }
    }
}
