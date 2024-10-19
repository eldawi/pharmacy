package com.mycompany.pharmacyapp;


public class Drug {
    private String name;
    private int id;
    private double price;
    private String category;
    private int Quantity;

    public Drug(String name, int id, double price, String category, int Quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.category = category;
        this.Quantity = Quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    
     
}
