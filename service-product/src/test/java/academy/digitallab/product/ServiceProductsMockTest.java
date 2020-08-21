package academy.digitallab.product;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;
import academy.digitallab.product.repository.ProductsRepository;
import academy.digitallab.product.service.ServiceProducts;
import academy.digitallab.product.service.ServiceProductsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ServiceProductsMockTest {

    @Mock
    private ProductsRepository productsRepository;

    private ServiceProducts serviceProducts;

    @BeforeEach
    public void setup(){/*
        MockitoAnnotations.initMocks(this);
        serviceProducts =  new ServiceProductsImpl( productsRepository);
        Products computer =  Products.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productsRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productsRepository.save(computer)).thenReturn(computer);*/
    }


    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Products found = serviceProducts.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Products newStock = serviceProducts.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
