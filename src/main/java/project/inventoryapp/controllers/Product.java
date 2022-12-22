package project.inventoryapp.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the product class. */
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
        setStock(stock);
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

    /** @return the id. */
    public int getId() {
        return id;
    }

    /** @param id the id to set. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return the name. */
    public String getName() {
        return name;
    }

    /** @param name the name to set. */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the price. */
    public double getPrice() {
        return price;
    }

    /** @param price the price to set. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @return the stock. */
    public int getStock() {
        return stock;
    }

    /** @param stock the stock to set. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** @return the min. */
    public int getMin() {
        return min;
    }

    /** @param min the min to set. */
    public void setMin(int min) {
        this.min = min;
    }

    /** @return the max. */
    public int getMax() {
        return max;
    }

    /** @param max the max to set. */
    public void setMax(int max) {
        this.max = max;
    }

    /** method to add a part to the associated parts list.
     @param part part to add. */
    public void addAssociatedPart(Part part){

        associatedParts.add(part);
    }

    /** method to delete a part to the associated parts list.
     @param selectedAssociatedPart part to add.
     @return boolean based on if it found a part to delete.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){

        return associatedParts.remove(selectedAssociatedPart);
    }

    /** method to get the product's associated parts list.
     @return the associatedParts list. */
    public  ObservableList<Part> getAllAssociatedParts() {
        //FIX ME - remove print below:
        return associatedParts;
    }



}
