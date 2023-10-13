package org.example.service;

import org.example.controller.Login;
import org.example.controller.OrderController;


public class AppService {


    private static boolean exit = false;

    public void setExit(boolean exit) {
        AppService.exit = exit;
    }


    public void run(){
        OrderController orderController = new OrderController();
        Login login = new Login();
        LoginService loginService = new LoginService();
        ProductService productService = new ProductService();
        // connect ke database
        ConnectDb.main();
        // get all data product in database
        productService.main();
        //login user
        loginService.main();
        //create order
        OrderService orderService = new OrderService();
        orderService.main();
        //menampilkan view home selamat datang
        orderController.home();
        while (!exit){
            orderController.orderMenu();
        }



    }
}
