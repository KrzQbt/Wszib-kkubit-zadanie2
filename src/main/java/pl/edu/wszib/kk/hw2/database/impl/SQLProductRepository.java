package pl.edu.wszib.kk.hw2.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.kk.hw2.database.IProductsRepository;
import pl.edu.wszib.kk.hw2.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLProductRepository implements IProductsRepository {
    @Autowired
    Connection connection;

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM tproduct WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("amount"));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE tproduct SET name = ?,description = ?, type =?, amount = ? WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescpription());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setInt(4, product.getAmount());
            preparedStatement.setInt(5,product.getId());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tproduct;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("amount")));
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return products;
    }
    // FINISH

    @Override
    public List<Product> getProductByName(String name) {
            List<Product> productsByName = new ArrayList<>();
            try {
                String sql = "SELECT * FROM tproduct WHERE name = ?;";
                PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
                preparedStatement.setString(1,name);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    productsByName.add(new Product(resultSet.getInt("id"),
                            name,
                            resultSet.getString("description"),
                            resultSet.getString("type"),
                            resultSet.getInt("amount")));
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }

            return productsByName;

    }
}
