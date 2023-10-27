package com.example.demo.view;

import org.springframework.stereotype.Component;

@Component
public class UserView {
    public static void manageUserCustomerMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2+"MANAGE USER");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add New User");
        System.out.println("2. Update User");
        System.out.println("3. Delete User");
    }
    public void inputOptionUserMeny(){
        System.out.print("Select Menu Option => ");
    }

    public void addUser(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + "ADD NEW USER");
        System.out.println(Utils.LINE);
    }

    public void deleteUser(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + "DELETE USER");
        System.out.println(Utils.LINE);
    }

}
