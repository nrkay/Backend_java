package com.example.demo.controller;


import com.example.demo.view.HomeMenu;
import com.example.demo.view.MerchantView;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.OperationsException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

@Component
public class HomeController {
    @Autowired
    MerchantController merchantController;

    @Autowired
    ProductController productController;
    @Autowired
    OrderController orderController;

    @Autowired
    UserController userController;
    private boolean exit = false;

    public void setExis(boolean exit){
        this.exit = exit;
    }
    public void home(){
        while (!exit){
            HomeMenu.welcomeMessage();
            HomeMenu.mainMenuOption();
            selectMainMenu();
        }
    }

    public void selectMainMenu(){
        HomeMenu.inputOptionMainMenu();
        try{
            Scanner scan = new Scanner(System.in);
            int mainMenuSelected = scan.nextInt();
            if(mainMenuSelected==1){
                merchantController.index();
            } else if (mainMenuSelected==2) {
                productController.index();
            } else if (mainMenuSelected==3) {
               userController.index();
            } else if (mainMenuSelected==4) {
                orderController.index();
            } else {
                HomeMenu.orderNotFound();
            }
        } catch (NumberFormatException  e){
            HomeMenu.orderNotFound();
        }

    }




}
