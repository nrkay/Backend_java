package org.example.service;

import org.example.model.Data;
import org.example.model.User;

import java.sql.*;

public class userTable {
    public static void main() {
        System.out.println("Mysqk JDBC Connection Testing");
        String SQL_SELECT = "Select * from Product";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/synrg3", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");

                // inisiasi object user
                User users = new User();
                users.setId(id);
                users.setName(name);
                users.setEmail(email);

                //add ke list user di model data
                Data.users.add(users);
            }
            Data.users.forEach(x -> System.out.println(x.getName()));

        } catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
