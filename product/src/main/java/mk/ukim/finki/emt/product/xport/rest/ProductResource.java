package mk.ukim.finki.emt.product.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.product.domain.models.Product;
import mk.ukim.finki.emt.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }
}
