package academy.digitallab.product;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;
import academy.digitallab.product.repository.ProductsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductsRepositoryMockTest {
    
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Products p1 = Products.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1250.99"))
                .status("Created")
                .createAt(new Date()).build();
        productsRepository.save(p1);

        List<Products> founds = productsRepository.findByCategory(p1.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);
    }
}
