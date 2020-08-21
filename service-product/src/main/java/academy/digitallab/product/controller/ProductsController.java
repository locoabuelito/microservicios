package academy.digitallab.product.controller;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;
import academy.digitallab.product.service.ServiceProducts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/products")
public class ProductsController {

    @Autowired
    private ServiceProducts serviceProducts;

    @GetMapping
    public ResponseEntity<List<Products>> listProductsByCategory(@RequestParam(name="categoryId", required = false)
                                                                   Long categoryId){
        List<Products> products = new ArrayList<>();

        if (categoryId == null){
            products = serviceProducts.listAllProducts();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else {
            products = serviceProducts.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping(value = "{/id}")
    public ResponseEntity<Products> getProducts(@PathVariable("id") Long id){
        Products products = serviceProducts.getProduct(id);
        if (products == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Products> createProducts(@Valid
            @RequestBody Products products, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Products productsCreate = serviceProducts.createProduct(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsCreate);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Products> updateProducts(@PathVariable("id") Long id, @RequestBody Products products){
        products.setId(id);
        Products productsDB =  serviceProducts.updateProduct(products);
        if (productsDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productsDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Products> deleteProducts(@PathVariable("id") Long id){
        Products productsDelete = serviceProducts.deleteProduct(id);
        if (productsDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productsDelete);
    }

    @PutMapping (value = "/{id}/stock")
    public ResponseEntity<Products> updateStockProducts(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity){
        Products products = serviceProducts.updateStock(id, quantity);
        if (products == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessages errorMessage = ErrorMessages.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
