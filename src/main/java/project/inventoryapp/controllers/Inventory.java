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

    public static Part lookUpPart(int PartId){
        //code to find a part by using partId
      // Part valueToReturn;
        for(int i = 0; i<allParts.size(); ++i){
            if(allParts.get(i).getId()==PartId){
              return allParts.get(i);
            }
            else{
                return null;
            }

        }
        return null;
    }

    public Product lookUpProduct(int ProductId){
        //code to return a product using productID
        return null;
    }

    public ObservableList<Part> lookUpPart(String partName){
        //code to return parts using part name
        return null;
    }

    public ObservableList<Product> lookUpProduct(String productName){
        //code to return product using product name
        return null;
    }

    public void updatePart(int index, Part selectedPart){
        //update part information
    }

    public void updateProduct(int index, Product newProduct){
        //update product information
    }

    public static boolean deletePart(Part selectedPart){
        //delete part from allParts list
        return allParts.remove(selectedPart);
    }

    public boolean deleteProduct(Product selectedProduct){
        //delete product from allProducts list
        return false;
    }

}
