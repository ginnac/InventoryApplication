package project.inventoryapp.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is the Inventory class. It contains the lists of Parts and Products. */
public class Inventory{

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This method renders the list of all parts.
      @return Returns allParts array. */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** This method renders the list of all products.
     @return Returns allProducts array. */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /** This method adds a part to the allParts array.
     @param part the part object to add. */
    public static void addPart(Part part){
        //code adds part passed to the method to the list of parts
        allParts.add(part);
    }

    /** This method adds a product to the allProducts array.
     @param product the product object to add. */
    public static void addProduct(Product product){
        //code adds product passed to the method to the list of products
        allProducts.add(product);
    }

    /** This method looks for a part in the allParts array.
     @param partId the part's Id to find.
     @return part or null.
     */
    public static Part lookUpPart(int partId){

        for(int i = 0; i < allParts.size(); i++){
            Part part = allParts.get(i);
            if(part.getId() == partId){
              return part;
            }
        }
        return null;
    }

    /** This method looks for a product in the allProducts array.
     @param productId the product's Id to find.
     @return product or null. */
    public static Product lookUpProduct(int productId){

        for(int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /** This method looks for a part in the allParts array.
     @param partName the part's name to find.
     @return partMatches a list with all matches in array. */
    public static ObservableList<Part> lookUpPart(String partName){

        ObservableList<Part> partMatches = FXCollections.observableArrayList();

        for(Part part: allParts){
            if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                partMatches.add(part);
            }
        }

        return partMatches;
    }

    /** This method looks for a product in the allProducts array.
     @param productName the product's name to find.
     @return productMatches a list with all matches in array. */
    public static ObservableList<Product> lookUpProduct(String productName){

        ObservableList<Product> productMatches = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                productMatches.add(product);
            }
        }

        return productMatches;
    }

    /** This method updates a part in the allParts array.
     @param index the index to find object.
     @param selectedPart the part object to update.
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index,selectedPart);
    }

    /** This method updates a product in the allProducts array.
     @param index the index to find object.
     @param newProduct the product object to update.
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index,newProduct);
    }

    /** This method deletes a part in the allParts array.
     @param selectedPart the part object to delete.
     @return boolean based on if it found a part to delete.
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /** This method deletes a product in the allProducts array.
     @param selectedProduct the product object to delete.
     @return boolean based on if it found a product to delete.
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

}
