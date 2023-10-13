package org.example.service;

import org.example.model.Data;
import org.example.model.Product;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class ProductService {
    public void main() {
       String SQL_SELECT = "SELECT p.id, p.Product_name, p.price, m.merchant_name FROM product p INNER JOIN merchant m ON p.merchant_id = m.id; ";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/synrg3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                long id = resultSet.getInt("id");
                String ProductName = resultSet.getString("Product_name");
                Long price = resultSet.getLong("price");
                String merchant_name = resultSet.getString("merchant_name");

                product.setProduct_name(ProductName);
                product.setId(id);
                product.setMerchant_name(merchant_name);
                product.setPrice(price);

                Data.products.add(product);
            }
        } catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
