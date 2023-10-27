package com.example.demo.controller;

import com.example.demo.model.Merchant;
import com.example.demo.model.Product;
import com.example.demo.service.MerchantService;
import com.example.demo.service.ProductService;
import com.example.demo.view.ErrorView;
import com.example.demo.view.ProductView;
import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ProductController {
    @Autowired
    ProductView productView;

    @Autowired
    MerchantService merchantService;
    @Autowired
    ProductService productService;
    public void index(){
        productView.manageProductMenuView();
        selectOptionMenu();
    }

    private void selectOptionMenu() {
        Scanner scan = new Scanner(System.in);
        productView.inputOptionProductMeny();
        int selectOption = scan.nextInt();
        try {
            if (selectOption == 1){
                //Add New Merchant
                addNewProduct();
            } else if (selectOption == 2) {
                //View All Merchant
                System.out.println("view all product");
                viewOpenProductMerchat();
            } else if (selectOption == 3) {
                System.out.println("3. View All Product in specific Merchant");
            } else if (selectOption == 4) {
                //delete product
                deleteProduct();
            } else if (selectOption == 5) {
                System.out.println("ga tau ah");
            } else if(selectOption == 6){
                System.out.println("bingung ah pengen beli truk");
            }
            else{
                ErrorView.orderNotFound();
            }
        } catch (InputMismatchException e){
            ErrorView.orderNotFound();
        }
    }

    private void addNewProduct() {
        productView.viewBannerAddProduct();
        Scanner scan = new Scanner(System.in);
        Product product = new Product();
        System.out.print("Product Name  => ");
        String nameProduct = scan.nextLine();
        product.setName(nameProduct);
        System.out.print("Product Price => ");
        long priceProduct = scan.nextInt();
        product.setPrice(priceProduct);
        System.out.print("Merchant Id   => ");
        UUID merchantId = UUID.fromString(scan.next());
        Merchant merchant = merchantService.findMerchantById(merchantId);
        product.setMerchant(merchant);
        productService.create(product);
        index();

    }

    private void deleteProduct(){
        productView.viewBannerDeleteProduct();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name Product => ");
        String nameProduct = scanner.nextLine();
        productService.deleteProduct(nameProduct);
        index();
    }

    private void viewOpenProductMerchat(){
        productView.viewOpenProductMerchant();
        index();
    }
}
