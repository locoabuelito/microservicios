package academy.digitallab.product.service;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;
import academy.digitallab.product.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceProductsImpl implements ServiceProducts{

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProduct(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public Products createProduct(Products products) {
        products.setStatus("CREATED");
        products.setCreateAt(new Date());
        return productsRepository.save(products);
    }

    @Override
    public Products updateProduct(Products products) {
       Products productsDB = getProduct(products.getId());
       if (productsDB == null){
           return null;
       }

       productsDB.setName(products.getName());
       productsDB.setDescription(products.getDescription());
       productsDB.setCategory(products.getCategory());
       productsDB.setPrice(products.getPrice());
       return productsRepository.save(productsDB);
    }

    @Override
    public Products deleteProduct(Long id) {
        Products productsDB = getProduct(id);
        if (productsDB == null){
            return null;
        }
        productsDB.setStatus("DELETED");
        return productsRepository.save(productsDB);
    }

    @Override
    public List<Products> findByCategory(Category category) {
        return productsRepository.findByCategory(category);
    }

    @Override
    public Products updateStock(Long id, Double quantity) {
        Products productsDB = getProduct(id);
        if (productsDB == null){
            return null;
        }
        Double stock = productsDB.getStock() + quantity;
        productsDB.setStock(stock);
        return productsRepository.save(productsDB);
    }
}
