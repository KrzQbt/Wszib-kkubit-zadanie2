package pl.edu.wszib.kk.hw2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kk.hw2.dao.IProductDao;
import pl.edu.wszib.kk.hw2.model.Product;
import pl.edu.wszib.kk.hw2.services.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDao productDao;

    @Override
    public Product getProductById(int id) {
        return this.productDao.getProductById(id);
    }



    @Override
    public void addProduct(Product newProduct) {

        System.out.println("add service " + newProduct.getName());
        this.productDao.addProduct(newProduct);

    }

    @Override
    public void deleteProductById(int id) {
        System.out.println("delete service " + id);
        this.productDao.deleteProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        Product productFromDB = this.productDao.getProductById(product.getId());
        productFromDB.setName(product.getName());
        productFromDB.setDescpription(product.getDescpription());
        productFromDB.setType(product.getType());
        productFromDB.setAmount(product.getAmount());

        this.productDao.updateProduct(productFromDB);
        System.out.println("update service");

    }

    @Override
    public List<Product> getAllProducts() {
        return this.productDao.getAllProducts();
    }
}
