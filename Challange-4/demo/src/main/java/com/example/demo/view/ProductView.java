package com.example.demo.view;

import com.example.demo.model.DTO.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductView {
    @Autowired
    ProductService productService;

    public void manageProductMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 +  Utils.TAB2  + Utils.TAB2 + "PRODUCT");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add New Product");
        System.out.println("2. View Already Product (Merchant: Open)");
        System.out.println("3. Edit Product");
        System.out.println("4. Delete Product");
    }

    public void viewOpenProductMerchant(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + Utils.TAB2 + "ALREADY PRODUCT");
        System.out.println(Utils.LINE);
        List<Product> product = productService.getOpenProductMerchant();
        for (Product d: product){
            System.out.println(d.getName() + " | " + d.getPrice() + " | " + d.getMerchant().getName());
        }
    }
    public void viewBannerAddProduct(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + Utils.TAB2 + "ADD PRODUCT");
        System.out.println(Utils.LINE);
    }


    public void viewBannerDeleteProduct(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + "DELETE PRODUCT");
        System.out.println(Utils.LINE);
    }


    public void inputOptionProductMeny(){
        System.out.print("Select Menu Option => ");
    }





}
