package pl.edu.wszib.kk.hw2.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.kk.hw2.dao.IProductDao;
import pl.edu.wszib.kk.hw2.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOimpl implements IProductDao {

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
    public void deleteProductById(int id) {
        String sql = "DELETE FROM tproduct WHERE id = ?";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void addProduct(Product product) {
        String sql ="INSERT INTO tproduct (name, description, type,amount)\n" +
                "VALUES (?,?,?,?);";
        System.out.println("insert dao");
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2,product.getDescpription());
            preparedStatement.setString(3,product.getType());
            preparedStatement.setInt(4,product.getAmount());
            preparedStatement.execute();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE tproduct SET name = ?,description = ?, type =?, amount = ? WHERE id = ?;";
        System.out.println("update dao");
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescpription());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setInt(4, product.getAmount());
            preparedStatement.setInt(5,product.getId());
            preparedStatement.executeUpdate();
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
            System.out.println("jestem w dao " +products.size());

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return products;
    }



}
