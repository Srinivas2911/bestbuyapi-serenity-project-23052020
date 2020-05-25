package com.bestbuyapi.model;

/*
Created by SP
*/

public class ProductPojo {

//"name": "New Product",
//        "type": "Hard Good",
//        "upc": "12345676",
//        "price": 99.99,
//        "description": "This is a placeholder request for creating a new product.",
//        "model": "NP12345"

private String name;
private String type;
private String upc;
private double price;
private String description;
private String model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
