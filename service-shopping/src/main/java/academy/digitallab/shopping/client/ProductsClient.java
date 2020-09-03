package academy.digitallab.shopping.client;

import academy.digitallab.shopping.model.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-product")
@RequestMapping(value = "/api/products")
public interface ProductsClient {

    @GetMapping(value = "{/id}")
    ResponseEntity<Products> getProducts(@PathVariable("id") Long id);
    @PutMapping(value = "/{id}/stock")
    ResponseEntity<Products> updateStockProducts(@PathVariable  Long id ,
        @RequestParam(name = "quantity", required = true) Double quantity);
}
