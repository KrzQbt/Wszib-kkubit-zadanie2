package pl.edu.wszib.kk.hw2.database;

import pl.edu.wszib.kk.hw2.model.Product;

import java.util.List;

public interface IProductsRepository {
    List<Product> getAllProducts();
    List<Product> getProductByName(String name);
    void  updateProduct(Product product);
    Product getProductById(int id);
}
