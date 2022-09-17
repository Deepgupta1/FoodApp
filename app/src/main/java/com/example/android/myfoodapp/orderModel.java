package com.example.android.myfoodapp;

public class orderModel {
    int orderImage;
    String soldItemName,price,orderNumber;

    public orderModel() {

    }

    public orderModel(int orderImage, String soldItemName, String price, String orderName) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderNumber = orderName;
    }




    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderName(String orderName) {
        this.orderNumber = orderName;
    }
}
