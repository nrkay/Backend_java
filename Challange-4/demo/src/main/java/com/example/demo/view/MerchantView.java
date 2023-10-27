package com.example.demo.view;

import com.example.demo.model.Merchant;
import com.example.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantView {
    @Autowired
    MerchantService merchantService;

    public static void manageMerchatMenuView(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2+"MERCHANT");
        System.out.println(Utils.LINE + Utils.NEW_LINE);
        System.out.println("1. Add New Merchant");
        System.out.println("2. View All Merchant");
        System.out.println("3. Open Merchant");
        System.out.println("4. Edit Merchant");
    }

    public  void viewAllMerchant(){
        List<Merchant> merchantList = merchantService.getAll();
        for (Merchant data: merchantList){
            System.out.println(data.getName() );
        }
    }

    public void viewBannerMerchant(){
        System.out.println(Utils.LINE);
        System.out.println(Utils.TAB2 + Utils.TAB2 + "ADD NEW MERCHANT");
        System.out.println(Utils.LINE);
    }



    public static void inputOptionMerchantMeny(){
        System.out.print("Select Menu Option => ");
    }
}
