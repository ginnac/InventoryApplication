package project.inventoryapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    //List should not be static, is not global, it doesnt apply to each product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    //Could write a second constructor to add the observable list to it.
    public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedPartsList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedPartsList;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part){

        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){

        return associatedParts.remove(selectedAssociatedPart);
    }

    public  ObservableList<Part> getAllAssociatedParts() {
        //FIX ME - remove print below:
        System.out.println("getAllAssociatedParts is pulling from Product.java");
        return associatedParts;
    }


}
