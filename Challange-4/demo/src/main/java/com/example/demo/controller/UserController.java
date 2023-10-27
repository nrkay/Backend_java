package com.example.demo.controller;

import com.example.demo.model.CustomerUser;
import com.example.demo.service.UserService;
import com.example.demo.view.HomeMenu;
import com.example.demo.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserController {
    @Autowired
    UserView userView;
    @Autowired
    UserService userService;
    public void index(){
        userView.manageUserCustomerMenuView();
        selectOption();
    }

    private void selectOption() {
        try{
            userView.inputOptionUserMeny();
            Scanner scan = new Scanner(System.in);
            int selectedOption = scan.nextInt();
            if (selectedOption == 1){
                //add user
                addUser();
            } else if (selectedOption == 2) {
                // edit user
            } else if (selectedOption == 3) {
                // delete user
                deleteUser();
            } else {
                HomeMenu.orderNotFound();
            }

        } catch (NumberFormatException  e){
            //error
            HomeMenu.orderNotFound();
        }
    }

    private void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username   => ");
        String username = scanner.nextLine();
        userService.deleteUser(username);

    }

    private void addUser() {
        userView.addUser();
        Scanner scan = new Scanner(System.in);
        CustomerUser customerUser = new CustomerUser();
        System.out.print("Username      => ");
        String username = scan.nextLine();
        customerUser.setUsername(username);
        System.out.print("Email Address => ");
        String email = scan.nextLine();
        customerUser.setEmail(email);
        System.out.print("Password      => ");
        String password = scan.nextLine();
        customerUser.setPasssword(password);
        userService.create(customerUser);

    }
}
