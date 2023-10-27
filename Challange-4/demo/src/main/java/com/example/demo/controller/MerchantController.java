package com.example.demo.controller;

import com.example.demo.model.Merchant;
import com.example.demo.service.MerchantService;
import com.example.demo.view.ErrorView;
import com.example.demo.view.MerchantView;
import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    @Autowired
    MerchantView merchantView;




    public void index(){
        MerchantView.manageMerchatMenuView();
        selectOptionMenu();
    }

    private void selectOptionMenu(){
        Scanner scan = new Scanner(System.in);
        MerchantView.inputOptionMerchantMeny();
        int selectOption= scan.nextInt();
        try {
            if (selectOption == 1){
                //Add New Merchant
                addNewMerchant();
            } else if (selectOption == 2) {
                //View All Merchant
                findAllMerchant();
            } else if (selectOption == 3) {
                //open merchant
                getOpenMerchant();
            } else if (selectOption == 4) {
                //edit Merchant
                editMerchant();
            } else{
                ErrorView.orderNotFound();
            }
        } catch (InputMismatchException e){
            ErrorView.orderNotFound();
        }
    }

    private void getOpenMerchant() {
        //List<Merchant> openMerchantList =
    }

    private void editMerchant() {
        Scanner scan = new Scanner(System.in);
        boolean isOpen;
        System.out.print("Name                 => ");
        String name = scan.nextLine();
        System.out.print("Close (F) / Open (T) => ");
        String status = scan.nextLine();
        if (status.equalsIgnoreCase("F")){
            isOpen=false;
        } else {
            isOpen=true;
        }
        merchantService.editMerchant(name, isOpen);
    }

    private void findAllMerchant(){
       merchantView.viewAllMerchant();
   }
    private void addNewMerchant(){
        Scanner scan = new Scanner(System.in);
        Merchant merchant = new Merchant();
        merchantView.viewBannerMerchant();
        System.out.print("Name Merchant     => ");
        String nameMerchant = scan.nextLine();
        merchant.setName(nameMerchant);

        System.out.print("Location Merchant => ");
        String locationMerchant = scan.nextLine();
        merchant.setLocation(locationMerchant);

        merchantService.create(merchant);
        //after create data, bact to index()
    }









}
