package com.example.demo.view;

import org.springframework.stereotype.Component;

@Component
public class OrderView {
    public void manageOrderView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 +  Utils.TAB2  + Utils.TAB2 + "ORDER");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
    }

    public void menu(){
        System.out.println("1. Order Food");
        System.out.println("2. Order and Pay");
    }
}
