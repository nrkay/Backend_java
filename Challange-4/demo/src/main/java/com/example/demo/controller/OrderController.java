package com.example.demo.controller;

import com.example.demo.model.CustomerUser;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.view.ErrorView;
import com.example.demo.view.OrderView;
import com.example.demo.view.ProductView;
import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class OrderController {
    @Autowired
    OrderView orderView;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ProductView productView;
    @Autowired
    OrderDetailService orderDetailService;


    CustomerOrder order = new CustomerOrder();
    UUID idUsername;

    public void index(){
        orderView.manageOrderView();
        //add order
        addOrder();
    }

    private void addOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Location  => ");
        String location = scanner.nextLine();
        order.setDestination(location);
        System.out.print("Id Username => ");
        UUID usernameId = UUID.fromString(scanner.next());
        idUsername = usernameId;
        CustomerUser customerUser = userService.findUserById(usernameId);
        order.setUser(customerUser);
        orderService.create(order);
        orderMenu();
    }

    private void orderMenu(){
        productView.viewOpenProductMerchant();
        orderView.menu();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Option => ");
            int option = scanner.nextInt();
            if (option == 1){
                menuOrder();
            } else if (option == 2) {
                payment();
            } else {
                System.out.println("Menu tidak ditemukan");
            }
        } catch (InputMismatchException e){
            ErrorView.orderNotFound();
        }
    }

    private void payment() {
        //looping semua product
        Scanner scanner = new Scanner(System.in);
        System.out.print("order_id => ");
        UUID orderId = UUID.fromString(scanner.next());
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderProduct(orderId);
        for (OrderDetail d:orderDetails){
            System.out.println(d.getProduct());
        }

       //mnampilkan total harga
        orderDetailService.getTotalPrice(orderId);
       //mengubah status order
        orderService.updateStatus(orderId);
    }

    private void menuOrder() {
        Scanner scan = new Scanner(System.in);
        OrderDetail orderDetail = new OrderDetail();
        System.out.print("Order Menu => ");
        String nameProduct = scan.nextLine();
        Product product1 = orderService.getProduct(nameProduct);
        //set product
        orderDetail.setProduct(product1);

        System.out.print("qty => ");
        int qty = scan.nextInt();
        int totalPrice = (int) (qty * product1.getPrice());
        orderDetail.setTotalPrice(totalPrice);
        orderDetail.setQuantity(qty);

        System.out.print("order_id => ");
        UUID orderId = UUID.fromString(scan.next());
        CustomerOrder customerOrder = orderService.findOrderById(orderId);
        orderDetail.setCustomerOrder(customerOrder);
        orderDetailService.create(orderDetail);
        orderMenu();

    }

}
