package project.inventoryapp.controllers;
//import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
///////////////////////////////////////////////////////////MIGHT HAVE TO IMPLEMENT OBSERVABLE LIST!
/** Inventory class. This class controls the list of Parts and Products. */
public class Inventory{
    /** Using observableArraylists because they work well with tableviews. */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllParts(){
        //pull in allParts array
        //FIX ME  - SYSTEM TEST PRINT BELOW
        System.out.println("getParts is pulling from Inventory.java");
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        //pull in allProducts array
        return allProducts;
    }

    public static void addPart(Part part){
        //code adds part passed to the method to the list of parts
        allParts.add(part);
    }

    public static void addProduct(Product product){
        //code adds product passed to the method to the list of products
        allProducts.add(product);
    }

    public static Part lookUpPart(int partId){
        //code to find a part by using partId

        for(int i = 0; i < allParts.size(); i++){
            Part part = allParts.get(i);
            if(part.getId() == partId){
              return part;
            }
        }
        return null;
    }

    public static Product lookUpProduct(int productId){
        //code to return a product using productID
        for(int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookUpPart(String partName){
        //code to return parts using part name
        ObservableList<Part> partMatches = FXCollections.observableArrayList();

        for(Part part: allParts){
            if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                partMatches.add(part);
            }
        }

        return partMatches;
    }

    public static ObservableList<Product> lookUpProduct(String productName){
        //code to return product using product name
        ObservableList<Product> productMatches = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                productMatches.add(product);
            }
        }

        return productMatches;
    }

    public static void updatePart(int index, Part selectedPart){
        //update part information
        allParts.set(index,selectedPart);

    }

    public static void updateProduct(int index, Product newProduct){
        //update product information
        allProducts.set(index,newProduct);
    }

    public static boolean deletePart(Part selectedPart){
        //delete part from allParts list
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct){
        //delete product from allProducts list
        return allProducts.remove(selectedProduct);
    }

}
