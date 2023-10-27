package org.example.view;

import org.example.controller.OrderController;
import org.example.model.Data;
import org.example.model.OrderDetail;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.LoginService;
import org.example.utils.Constant;

public class GeneralView {

    public static void welcomeMessage(){
        System.out.println(Constant.LINE);
        System.out.println(Constant.TAB2 + "WELCOME TO BINAR GO-FOOD");
        System.out.println(Constant.LINE);
    }

    public static void menuOption(){
        User user = new User();
        LoginService loginService = new LoginService();
        System.out.println(Constant.LINE);
        for (User item: Data.users){
            System.out.println(Constant.TAB2 + Constant.TAB2 + "Hallo, " + item.getName());
        }
        System.out.println( Constant.TAB2 +"Mau Makan Apa Hari ini ??\n");
        for (Product item: Data.products){
            System.out.println(item.getId() + " | " + item.getProduct_name() + " | " + item.getPrice() + " | " + item.getMerchant_name());
        }
        System.out.println("98. Lihat List Orderan Anda");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi\n");
        System.out.print("Masukkan pilihan anda => ");
    }

    public static void listOrderan(){
        System.out.println(Constant.LINE);
        System.out.println("Berikut Pesanan Anda\n");
        for (OrderDetail item: Data.orderDetails){
            System.out.println(item.getProduct_name());
        }
        System.out.println(Constant.LINE);
    }

    public static void confirmationOrder(){
        System.out.println(Constant.LINE);
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println(Constant.LINE);
        if (Data.orderDetails.isEmpty()){
            System.out.println("Pesanan Kosong");
        } else {
            for (OrderDetail item: Data.orderDetails){
                System.out.println(item.getProduct_name());
            }
            System.out.println("1. Konfirmasi dan Pembayaran");
            System.out.println("2. Kembali Ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("=>");
            OrderController orderController = new OrderController();
            orderController.confirmationOrder();
        }
    }



}
