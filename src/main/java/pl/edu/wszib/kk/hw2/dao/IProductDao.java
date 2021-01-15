package pl.edu.wszib.kk.hw2.dao;

import pl.edu.wszib.kk.hw2.model.Product;

import java.util.List;

public interface IProductDao {
    Product getProductById(int id);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void deleteProductById(int id);

}
