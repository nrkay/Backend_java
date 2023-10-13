package org.example.controller;

import org.example.model.Data;
import org.example.model.OrderDetail;
import org.example.model.Product;
import org.example.service.AppService;
import org.example.service.OrderDetailService;
import org.example.service.OrderService;
import org.example.utils.Constant;
import org.example.view.ErrorView;
import org.example.view.GeneralView;

import java.util.InputMismatchException;
import java.util.Scanner;


public class OrderController {
    Scanner scanner = new Scanner(System.in);
    AppService appService = new AppService();

    public void home(){
        GeneralView.welcomeMessage();
    }

    public void orderMenu(){
        GeneralView.menuOption();
        int index = -1;
            int choice = scanner.nextInt();
            // check input is exist or not
            for(int i = 0; i < Data.products.size(); i++){
                if (Data.products.get(i).getId() == choice){
                    index = i;
                    break;
                }
            }

            // filter input choice
            if (index != -1){
                //input Order to database
                orderTrue(choice);
            } else if (choice == 97) {
                //todo
            } else if (choice == 98) {
                //menampilkan List Orderan
                GeneralView.listOrderan();
            } else if (choice == 99) {
                //pesan dan bayar
                GeneralView.confirmationOrder();
            } else if (choice == 0) {
                // program is stop
                appService.setExit(true);
            } else {
                // show error invalid input
                ErrorView.invalidInput();
            }
    }

    public void orderTrue(int keyMenu){
        System.out.println(Constant.LINE);
        System.out.println("Order Detail");
        System.out.println(Constant.LINE);
        System.out.println("Berapa Jumlah Pesanan =>");
        int inputQty = scanner.nextInt();
        scanner.nextLine();
        if (inputQty < 1){
            System.out.println("Minimal 1 Jumlah Pesanan");
        } else if (inputQty >= 1) {
            for (Product item: Data.products){
                if (keyMenu == item.getId()){
                    System.out.println(item.getProduct_name());
                    OrderDetail orderDetail = new OrderDetail(item.getProduct_name(), inputQty, item.getPrice());
                    Data.orderDetails.add(orderDetail);
                }
            }
        } else {
            ErrorView.invalidInput();
        }
    }

    public void confirmationOrder(){
        int choice = scanner.nextInt();
        if (choice == 1) {
            // input OrderDetail to Database
            OrderDetailService orderDetailService = new OrderDetailService();
            orderDetailService.main();
        } else if (choice == 2) {
            //kembali ke menu utama
            orderMenu();
        } else if (choice == 0) {
            appService.setExit(true);
        } else {
            ErrorView.invalidInput();
        }

    }


}
