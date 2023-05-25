package mk.ukim.finki.emt.product.domain.repository;

import mk.ukim.finki.emt.product.domain.models.Product;
import mk.ukim.finki.emt.product.domain.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
