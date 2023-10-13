package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {
    public static void main() {
        //connection dengan database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/synrg3", "root", ""
        )){
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
