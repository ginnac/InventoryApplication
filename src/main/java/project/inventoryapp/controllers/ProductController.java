package project.inventoryapp.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static project.inventoryapp.controllers.InventoryController.pageLoader;

/** This is the ProductController class. This class handles the product fxml file. */
public class ProductController implements Initializable {

    Stage stage;
    Parent scene;
    public Label errorMessage;
    public TextField partsSearchBox;
    public Label productPageTitle;
    public TextField idProduct;
    public TextField nameProduct;
    public TextField invProduct;
    public TextField priceProduct;
    public TextField maxProduct;
    public TextField minProduct;
    public TableView<Part> allPartsTable;
    public TableColumn<Part, Integer> allPartsIdCol;
    public TableColumn<Part, String> allPartsNameCol;
    public TableColumn<Part, Integer> allPartsInvCol;
    public TableColumn<Part, Double> allPartsPriceCol;
    public TableColumn<Part, Integer> allPartsMaxCol;
    public TableColumn<Part, Integer> allPartsMinCol;
    public TableView<Part> associatedPartsTable;
    public TableColumn<Part, Integer> assocPartsIdCol;
    public TableColumn<Part, String> assocPartsNameCol;
    public TableColumn<Part, Integer> assocPartsInvCol;
    public TableColumn<Part, Double> assocPartsPriceCol;
    public TableColumn<Part, Integer> assocPartsMaxCol;
    public TableColumn<Part, Integer> assocPartsMinCol;
    public Button addAssocPartBtn;
    public Button removeAssocPartBtn;
    public Button saveProductBtn;
    public Button productCancelBtn;
    public ObservableList<Part> newFullList = FXCollections.observableArrayList();
    public ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();

    private static ObservableList<String> productErrorList = FXCollections.observableArrayList();

    private int index;

    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newFullList.addAll(Inventory.getAllParts());

        //Render when adding a Part or Modifying a Part
        productPageTitle.setText(InventoryController.getPageTitle());
        allPartsTable.setItems(newFullList);
        populateTable(allPartsIdCol, allPartsNameCol, allPartsPriceCol, allPartsInvCol, allPartsMinCol, allPartsMaxCol);

        if(InventoryController.getPageTitle() == "Modify Product") {

            // MODIFY ONLY. Add values as needed
            cloneArray();
            associatedPartsTable.setItems(InventoryController.getSelectedProduct().getAllAssociatedParts());
            populateTable(assocPartsIdCol, assocPartsNameCol, assocPartsPriceCol, assocPartsInvCol, assocPartsMinCol, assocPartsMaxCol);

            idProduct.setText(toString(InventoryController.getSelectedProduct().getId()));
            nameProduct.setText(InventoryController.getSelectedProduct().getName());
            invProduct.setText(toString(InventoryController.getSelectedProduct().getStock()));
            priceProduct.setText(toString(InventoryController.getSelectedProduct().getPrice()));
            minProduct.setText(toString(InventoryController.getSelectedProduct().getMin()));
            maxProduct.setText(toString(InventoryController.getSelectedProduct().getMax()));
        }
        else{
            //ADD PRODUCT ONLY
            index = Inventory.getAllProducts().size() + 1;
            idProduct.setText(toString(index));
            associatedPartsTable.setItems(tempAssociatedParts);
            populateTable(assocPartsIdCol, assocPartsNameCol, assocPartsPriceCol, assocPartsInvCol, assocPartsMinCol, assocPartsMaxCol);

        }

    }

    /** This is the cloneArray method. Copy's the products associated parts list to the temp associated parts list. */
    public void cloneArray(){
        tempAssociatedParts.removeAll();
        for(int i = 0 ; i<InventoryController.getSelectedProduct().getAllAssociatedParts().size(); i++){
            tempAssociatedParts.add(InventoryController.getSelectedProduct().getAllAssociatedParts().get(i));
        }
    }

    /** This toString method is used to cast an integer to String.
     @param index index to cast.
     @return index in string form. */
    private String toString(int index) {
        return "" + index + "";
    }

    /** This toString method is used to cast a double to String.
     @param index index to cast.
     @return index in string form. */
    private String toString(double index) {
        return "" + index + "";
    }

    /** This is the populateTable method. It populates the tables in the Product page with the passed values.
     @param id the id colum.
     @param name the name column.
     @param price the price column.
     @param stock the stock column.
     @param min the min column.
     @param max the max column. */
    public void populateTable(TableColumn<?, Integer> id, TableColumn<?, String> name, TableColumn<?, Double> price, TableColumn<?, Integer> stock, TableColumn<?, Integer> min, TableColumn<?, Integer> max){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        min.setCellValueFactory(new PropertyValueFactory<>("min"));
        max.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

    /** This is the pageLoader method. This method loads different fxml based on the parameters entered.
     @param actionEvent the event to pass.
     @param path the fxml path.
     @param elementType the type of element was clicked on. */
    public void pageLoader(ActionEvent actionEvent, String path, String elementType){

        /** Casting event source and determining where event source comes from. */
        if (elementType == "radioButton"){
            stage = (Stage)((RadioButton)actionEvent.getSource()).getScene().getWindow();
        }
        else{
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        }

        /** Then we can reference the proper fxml document. */
        try {
            scene = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();
    }


    /** This is the onClickAddAssocPart method. This method adds the selected part to the associated parts list.
     @param actionEvent the event to pass. */
    public void onClickAddAssocPartBtn(ActionEvent actionEvent) {
        //System.out.println("Add part in product screen was clicked");
        errorMessage.setText("");

            Part part = allPartsTable.getSelectionModel().getSelectedItem();
            //No need to remove the item from the top list only add it tp the assoc list
            //newFullList.remove(part);
            if(part != null) {
                //FOR MODIFY ONLY
                if (productPageTitle.getText() == "Modify Product") {
                    Inventory.lookUpProduct(InventoryController.getSelectedProduct().getId()).addAssociatedPart(part);
                }
                //FOR ADD ONLY
                else {
                    tempAssociatedParts.add(part);
                }
            }
            else {
                errorMessage.setText("Part not selected. Please select part.");
            }
    }

    /** This is the onClickRemoveAssocPart method. This method prompts the user to confirm to remove the selected part to the associated parts list.
     @param actionEvent the event to pass. */
    public void onClickRemoveAssocPartBtn(ActionEvent actionEvent) {

        errorMessage.setText("");

            try {
                Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + part.getName() + " as an associated item?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {

                    if (productPageTitle.getText() == "Modify Product") {
                        Inventory.lookUpProduct(InventoryController.getSelectedProduct().getId()).deleteAssociatedPart(part);
                        System.out.println("remove part in product screen was clicked");
                    }
                    else {
                        //FOR ADD ONLY
                        tempAssociatedParts.remove(part);
                    }
                }
                else{
                    System.out.println("Cancelled was clicked");
                    associatedPartsTable.getSelectionModel().clearSelection();
                }
            }
            catch(Exception e){
                errorMessage.setText("Part not selected. Please select part.");
            }
    }

    /** This is the onClickSaveProduct method. This method saves a new product or modified product to the products list.
     @param actionEvent the event to pass. */
    public void onClickSaveProductBtn(ActionEvent actionEvent) {
        //clear error message field if trying to save again
        errorMessage.setText("");
        try {
            checkForErrors(nameProduct.getText(), "string", "Name");
            checkForErrors(invProduct.getText(), "integer", "Inv");
            checkForErrors(priceProduct.getText(), "double", "Price/Cost");
            checkForErrors(maxProduct.getText(), "integer", "Max");
            checkForErrors(minProduct.getText(), "integer", "Min");

            if ((Integer.parseInt(minProduct.getText()) <= Integer.parseInt(maxProduct.getText())) && (Integer.parseInt(invProduct.getText()) >= Integer.parseInt(minProduct.getText()))
                    && (Integer.parseInt(invProduct.getText()) <= Integer.parseInt(maxProduct.getText()))) {
                //MODIFY
                if (productPageTitle.getText() == "Modify Product") {
                    //get index of selected Part
                    int objIndex = Inventory.getAllProducts().indexOf(InventoryController.getSelectedProduct());
                    //build a object with entered information
                    Product tempProduct = new Product(Integer.parseInt(idProduct.getText()), nameProduct.getText(), Double.parseDouble(priceProduct.getText()),
                            Integer.parseInt(invProduct.getText()), Integer.parseInt(minProduct.getText()), Integer.parseInt(maxProduct.getText()), InventoryController.getSelectedProduct().getAllAssociatedParts());
                    //call update product
                    Inventory.updateProduct(objIndex, tempProduct);

                } else {
                    //ADD PRODUCT
                    System.out.println("Add product");
                    Product productObj = new Product(index, nameProduct.getText(), Double.parseDouble(priceProduct.getText()), Integer.parseInt(invProduct.getText()), Integer.parseInt(minProduct.getText()), Integer.parseInt(maxProduct.getText()), tempAssociatedParts);
                    Inventory.addProduct(productObj);
                    tempAssociatedParts.removeAll();
                }

                pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
            }
            else{
                String errMsg = "Please enter valid values:";
                if(Integer.parseInt(minProduct.getText()) > Integer.parseInt(maxProduct.getText())){
                    errMsg += "\n" + "-" + "Max must be greater or equal than Min.";
                    errorMessage.setText(errMsg);
                }
                if((Integer.parseInt(minProduct.getText()) > Integer.parseInt(invProduct.getText())) || (Integer.parseInt(maxProduct.getText()) < Integer.parseInt(invProduct.getText())) ){
                    errMsg += "\n" + "-" + "Inv must between Min and Max ";
                    errorMessage.setText(errMsg);
                }
            }
        }
        catch(NumberFormatException e){
            String errMsg = "Please enter valid values:" ;
            //Render the parts error list below
            for (String str : getProductErrorList()){
                //System.out.println(str);
                errMsg += "\n" + "-" + str;
            }
            errorMessage.setText(errMsg);
            emptyErrorList();
        }
    }

    /** This is the onClickProductCancel method. This method undo changes to selected product's associated parts list. And cancels changes other changes to selected product.
     @param actionEvent the event to pass. */
    public void onClickProductCancelBtn(ActionEvent actionEvent) {

       if(productPageTitle.getText() == "Modify Product") {

           //If not saving update the assoc. parts list to elements before the changes
           int objIndex = Inventory.getAllProducts().indexOf(InventoryController.getSelectedProduct());
           //build a object with entered information
           Product tempProduct = new Product(InventoryController.getSelectedProduct().getId(),
                   InventoryController.getSelectedProduct().getName(),
                   InventoryController.getSelectedProduct().getPrice(),
                   InventoryController.getSelectedProduct().getStock(),
                   InventoryController.getSelectedProduct().getMin(),
                   InventoryController.getSelectedProduct().getMax(),
                   tempAssociatedParts);
           Inventory.updateProduct(objIndex,tempProduct);
        }

        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }


    /** This is the onSearchPartHandler method. This method handles parts searches in the Product screen.
     @param actionEvent the event to pass. */
    public void onSearchPartHandler(ActionEvent actionEvent) {

        allPartsTable.getSelectionModel().clearSelection();
        String keyword = partsSearchBox.getText();
        ObservableList<Part> resultsList = Inventory.lookUpPart(keyword);

        //remove condition of list size if it can search by id and name together
        //If searching by id.
        if(resultsList.size() == 0){

            try {
                allPartsTable.setItems(Inventory.getAllParts());
                populateTable(allPartsIdCol, allPartsNameCol, allPartsPriceCol, allPartsInvCol, allPartsMinCol, allPartsMaxCol);

                int numKeyword = Integer.parseInt(keyword);
                Part part = Inventory.lookUpPart(numKeyword);

                if (part != null) {
                    //resultsList.add(part);
                    allPartsTable.getSelectionModel().select(Inventory.getAllParts().indexOf(part));
                    allPartsTable.scrollTo(Inventory.getAllParts().indexOf(part));
                }

            }
            catch(NumberFormatException e){
                //ignore
            }
        }
        //If searching by name.
        else{
            allPartsTable.setItems(resultsList);
            populateTable(allPartsIdCol, allPartsNameCol, allPartsPriceCol, allPartsInvCol, allPartsMinCol, allPartsMaxCol);
            partsSearchBox.setText("");
        }

    }


    /** This is the checkForErrors method. This method handles data type exceptions for the add/modify product screens.
     @param value the value passed.
     @param type the type of data expected.
     @param field the field data was entered into. */
    public static void checkForErrors(String value, String type, String field){

        if(((type =="integer") || (type == "double")) && (value != "") ){
            //check that is not null and is numerical
            try{
                if(type =="integer") {
                    Integer.parseInt(value);
                }
                else{
                    Double.parseDouble(value);
                }
            }
            catch (NumberFormatException e){
                productErrorList.add(field + " has to be a numerical value.");
            }
        }
        //check that os not blank
        if(value == ""){
            productErrorList.add(field + " can not be blank.");
        }

    }

    /** This is the getProductErrorList. This method gets the product's list of errors.
     @return productErrorList. */
    public static ObservableList<String> getProductErrorList() {
        return productErrorList;
    }

    /** This is the emptyErrorList. This method clears the productErrorList array. */
    public static void emptyErrorList(){
        productErrorList.clear();
    }
}
