package project.inventoryapp.models;

import project.inventoryapp.controllers.PartController;

public class Product extends Part{
    public Product(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }
}
