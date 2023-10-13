package org.example.service;

import org.example.model.Data;
import org.example.model.Order;
import org.example.model.User;
import org.example.utils.Constant;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDateTime;
public class OrderService {
    Scanner scanner = new Scanner(System.in);

    private int user_id;
    public  void main() {
        User user = new User();
        Order order = new Order();
        System.out.println("Masukkan Alamat Anda : ");
        String address = scanner.next();

        System.out.println(user.getId());
        for (User item: Data.users){
            System.out.println("ini id nya " + item.getId());
            user_id = item.getId();
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/synrg3", "root", "");
             Statement statement = conn.createStatement()) {
            int row = statement.executeUpdate(generateInsert(address, user_id));
            System.out.println("setelah diubah " + user_id);
            order.setUser_id(user_id);
            order.setDestination_address(address);
            Data.orders.add(order);

        } catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static String generateInsert(String destination_address, int user_id) {
        return "INSERT INTO `ORDER` (CREATED_DATE, DESTINATION_ADDRESS, USER_ID) " +
                "VALUES ('" + LocalDateTime.now() + "', '" + destination_address + "', " + user_id + ")";
    }

}
