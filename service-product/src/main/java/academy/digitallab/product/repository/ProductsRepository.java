package academy.digitallab.product.repository;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    public List<Products> findByCategory(Category category);
}
