package pl.edu.wszib.kk.hw2.services;

import pl.edu.wszib.kk.hw2.model.Product;
import java.util.List;

public interface IProductService {
    Product getProductById(int id);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    void addProduct(Product product);
    public void deleteProductById(int id);
}

