package org.example.service;
import org.example.model.Data;
import org.example.model.Product;
import org.example.model.User;
import org.example.utils.Constant;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;


public class OrderDetailService {

    Product product = new Product();
    public  void main() {
        Scanner scanner = new Scanner(System.in);

        // membutuhkan id product
        // membutuhkan id order

        //untuk input ke database, looping semua isi yang ada di ArrayList Order Detail
        for (int i = 0; i < Data.orderDetails.size(); i++){
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/synrg3", "root", "");
                 Statement statement = conn.createStatement()) {
                int row = statement.executeUpdate(generateInsert(address, user_id));
                order.setUser_id(user_id);
                order.setDestination_address(address);
                Data.orders.add(order);

            } catch (SQLException e){
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static String generateInsert(int OrderId, int ProductId, int qty, int price) {
        return "INSERT INTO `ORDERDETAIL` (ORDER_ID, PRODUCT_ID, QUANTITY, PRICE) " +
                "VALUES ('" + OrderId + "', '" + ProductId + "', '" + qty + "',  '" + price + "')";
    }
}
