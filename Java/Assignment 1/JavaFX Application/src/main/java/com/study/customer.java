package com.study;

//  =======================================
//  Inherit user to customer (constructor)
//  =======================================
public class customer extends user{
    public customer(String name, String email, String password) {
        super(name, email, password, "Customer");
    }

public void placeOrder () {
        System.out.println("Customer place order");
}

public void viewOrders () {
        System.out.println("Customer can check their orders");
}

}
