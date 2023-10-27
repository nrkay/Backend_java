package com.example.demo.view;

public class HomeMenu {

    public static void welcomeMessage(){
        System.out.println("Selamat datang di BinarFud");
    }

    public static void mainMenuOption(){
        System.out.println("Silahkan pilih menu:");
        System.out.println("1. Manage Merchant");
        System.out.println("2. Manage Product");
        System.out.println("3. Manage User");
        System.out.println("4. Order");
    }

    public static void manageMerchatMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2+"MERCHANT");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add New Merchant");
        System.out.println("2. View All Merchant");
        System.out.println("3. View All Product in specific Merchant");
        System.out.println("4. Edit Merchant");
        System.out.println("5. Delete Merchant");
    }

    public static void menageProductMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2+"PRODUCT");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add new Product");
        System.out.println("2. View All Product");
        System.out.println("3. Edit Product");
        System.out.println("4. Delete Product");
    }

    public static void menageUserMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2+"USER");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add new user");
        System.out.println("2. Edit user");
        System.out.println("3. Delete user");
    }

    public static void orderNotFound(){
        System.out.println("Menu tidak tersedia, silahkan coba lagi");
    }

    public static void orderMenu(){
        System.out.println("ini menu order");
    }

    public static void inputOptionMainMenu(){
        System.out.print("Select Menu Option => ");
    }


}
