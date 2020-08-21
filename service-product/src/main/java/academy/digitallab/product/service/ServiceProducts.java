package academy.digitallab.product.service;

import academy.digitallab.product.entity.Category;
import academy.digitallab.product.entity.Products;

import java.util.List;

public interface ServiceProducts {
    public List<Products> listAllProducts();
    public Products getProduct(Long id);
    public Products createProduct (Products products);
    public Products updateProduct (Products products);
    public Products deleteProduct (Long id);
    public List<Products> findByCategory(Category category);
    public Products updateStock (Long id, Double quantity);
}
