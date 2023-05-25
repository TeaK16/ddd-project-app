package mk.ukim.finki.emt.product.service;

import mk.ukim.finki.emt.product.domain.models.Product;
import mk.ukim.finki.emt.product.domain.models.ProductId;
import mk.ukim.finki.emt.product.service.form.ProductForm;

import java.util.List;

public interface ProductService {

    Product findById(ProductId id);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity);
    Product orderItemRemoved(ProductId productId, int quantity);
    List<Product> getAll();

}
