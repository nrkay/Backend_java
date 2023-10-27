package org.example.service;
import org.example.model.Data;
import org.example.model.User;
import org.example.utils.Constant;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class LoginService {
    Scanner scanner = new Scanner(System.in);
    public User main(){
        User users = new User();
        System.out.println(Constant.LINE);
        System.out.println(Constant.TAB2 + Constant.TAB2 + "Login Page");
        System.out.println(Constant.LINE);
        System.out.println("Masukkan Username : ");
        String username = scanner.next();
        scanner.nextLine();
        System.out.println("Masukkan Email :");
        String email = scanner.next();
        scanner.nextLine();
        // untuk id dibuat generate auto
        Random random = new Random();
        int id = random.nextInt(); // Menghasilkan angka bulat acak dalam seluruh rentang Integer.
        System.out.println(Constant.LINE);
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/synrg3", "root", "");
             Statement statement = conn.createStatement()) {
            int row = statement.executeUpdate(generateInsert(username, email, id));
            users.setId(id);
            users.setEmail(email);
            users.setName(username);
            Data.users.add(users);

        } catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    private static String generateInsert(String username, String email, int id){
        return "INSERT INTO USER (USERNAME, EMAIL, ID)" + "VALUES ('" + username + "', '" + email + "', '" + id + "')";
    }
}
