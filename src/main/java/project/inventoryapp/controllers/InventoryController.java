package project.inventoryapp.controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/** This is the InventoryController class. This class handles methods for the Inventory fxml file. */
public class InventoryController  implements Initializable{

    static Stage stage;
    static Parent scene;
    @FXML
    public TableView <Part> partsTable;
    @FXML
    public Button exitBtn;
    @FXML
    public TableColumn <Part, Integer> partsIdColumn;
    @FXML
    public TableColumn <Part, String> partsNameColumn;
    @FXML
    public TableColumn <Part, Double> partsPriceColumn;
    @FXML
    public TableColumn <Part, Integer> partsStockColumn;
    @FXML
    public TableColumn <Part, Integer> partsMinColumn;
    @FXML
    public TableColumn <Part, Integer> partsMaxColumn;
    @FXML
    public Button partsDeleteBtn;
    @FXML
    public TableView <Product> productsTable;
    @FXML
    public TableColumn <Product, Integer> productsIdColumn;
    @FXML
    public TableColumn <Product, String> productsNameColumn;
    @FXML
    public TableColumn <Product, Double> productsPriceColumn;
    @FXML
    public TableColumn <Product, Integer> productStockColumn;
    @FXML
    public TableColumn <Product, Integer>  productMinColumn;
    @FXML
    public TableColumn <Product, Integer> productMaxColumn;
    @FXML
    public TextField partsSearchBar;
    @FXML
    public TextField productsSearchBar;
    @FXML
    public Button partsModifyBtn;
    @FXML
    public Button partsAddBtn;
    @FXML
    public Button productsDeleteBtn;
    @FXML
    public Button productsModifyBtn;
    @FXML
    public Button productsAddBtn;

    //Values to conditionally transfer to other pages
    private static String  pageTitle;
    public Label errorMessage;

    public static String getPageTitle() {
        return pageTitle;
    }

    private static Part selectedPart;
    public static Part getSelectedPart() {
        return selectedPart;
    }

    private static Product selectedProduct;
    public static Product getSelectedProduct() {
        return selectedProduct;
    }
    private static String conditionalField;
    public static String getConditionalField() {
        return conditionalField;
    }



    /** This is the Initialize method. It loads up as soon as the associated page loads up. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I was initialized");

        // 3 Test data items below -  FIX ME AS NEEDED:
        partsTable.setItems(Inventory.getAllParts());
        populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);

        productsTable.setItems(Inventory.getAllProducts());
        populateTable(productsIdColumn, productsNameColumn, productsPriceColumn, productStockColumn, productMinColumn, productMaxColumn);

    }

    /** This is the populateTable method. It populates the tables in the Inventory page with the passed values.
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
    public static void pageLoader(ActionEvent actionEvent, String path, String elementType){

        /** Casting event source and determining where event source comes from. */
        if (elementType == "radioButton"){
            stage = (Stage)((RadioButton)actionEvent.getSource()).getScene().getWindow();
        }
        else{
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        }

        /** Then we can reference the proper fxml document. */
        try {
            scene = FXMLLoader.load(InventoryController.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();
    }


    /** This is the onClickAddPart method. This method loads the inHouse fxml.
     @param actionEvent the event to pass. */
    public void onClickAddPartBtn(ActionEvent actionEvent) {
        pageTitle = "Add Part";
        pageLoader(actionEvent, "/project/inventoryapp/inhouse.fxml", "button");

    }

    /** This is the onClickDeletePart method. This method prompts a confirmation box. If users agrees, the selected part is deleted.
     @param actionEvent the event to pass. */
    public void onClickDeletePartBtn(ActionEvent actionEvent) {
        System.out.println("Delete part button was clicked");

        try {
            Part part = partsTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + part.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                //FIX ME!!!! - Change this so it can take any id number
                if (Inventory.lookUpPart(part.getId()) != null) {
                    Inventory.deletePart(Inventory.lookUpPart(part.getId()));
                } else {
                    System.out.println("Part not found");
                }

                pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");

            } else {
                partsTable.getSelectionModel().clearSelection();
                //System.out.println("Cancelled was clicked");
                //pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
            }
        }
         catch(Exception e){
             errorMessage.setText("Part not selected. Please select part.");
        }
    }


    /** This is the onClickModifyPart method. This method loads the inHouse/Outsourced part fxml.
     @param actionEvent the event to pass. */
    public void onClickModifyPartBtn(ActionEvent actionEvent) {

        System.out.println("Modify part button was clicked");
        pageTitle = "Modify Part";
        try {
            //grab selected part
            Part part = partsTable.getSelectionModel().getSelectedItem();

            //Store object as static value
            selectedPart = part;

            //load proper page based on instance type
            if (part instanceof InHouse) {
                conditionalField = String.valueOf(((InHouse) part).getMachineId());
                pageLoader(actionEvent, "/project/inventoryapp/inhouse.fxml", "button");
            } else {
                conditionalField = ((Outsourced) part).getCompanyName();
                pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml", "button");
            }
        }
        catch(Exception e){
            errorMessage.setText("Part not selected. Please select part.");
        }
    }

    /** This is the onClickModifyProduct method. This method loads the product fxml.
     @param actionEvent the event to pass. */
    public void onClickModifyProductBtn(ActionEvent actionEvent) {

        pageTitle = "Modify Product";

        try {
            //grab selected part
            Product product = productsTable.getSelectionModel().getSelectedItem();

            //Store object as static value
            selectedProduct = product;
            pageLoader(actionEvent, "/project/inventoryapp/product.fxml", "button");
        }
        catch(Exception e){
            errorMessage.setText("Product not selected. Please select product.");
        }

    }

    /** This is the onClickAddProduct method. This method loads the product fxml.
     @param actionEvent the event to pass. */
    public void onClickAddProductBtn(ActionEvent actionEvent) {

        pageTitle = "Add Product";
        pageLoader(actionEvent, "/project/inventoryapp/product.fxml", "button");

    }

    /** This is the onClickDeleteProduct method. This method prompts the user to confirm if they want to delete the selected product. If the user agrees the product is deleted.
     @param actionEvent the event to pass. */
    public void onClickDeleteProductBtn(ActionEvent actionEvent) {
        System.out.println("delete product button was clicked");

            try {
                Product product = productsTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + product.getName() + "?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (Inventory.lookUpProduct(product.getId()) != null) {
                        if (Inventory.lookUpProduct(product.getId()).getAllAssociatedParts().size() == 0) {
                            Inventory.deleteProduct(Inventory.lookUpProduct(product.getId()));
                            pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
                        } else {
                            errorMessage.setText("Part(s) is associated with the selected Product. Please disassociate it(them) before deleting the Product.");
                        }
                    }

                } else {
                    partsTable.getSelectionModel().clearSelection();
                }
            } catch (Exception e) {
                errorMessage.setText("Product not selected. Please select product.");
            }
    }

    /** This is the onClickExit method. This method ends the program when the user clicks on the Exit button.
     @param actionEvent the event to pass. */
    public void onClickExitBtn(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** This is the partOnSearch method. This method handles searches. It returns part id or name matches.
     @param actionEvent the event to pass. */
    public void partOnSearchHandler(ActionEvent actionEvent) {

        partsTable.getSelectionModel().clearSelection();
        String keyword = partsSearchBar.getText();
        ObservableList<Part> resultsList = Inventory.lookUpPart(keyword);

        //If searching by id.
        if(resultsList.size() == 0){

            try {
                partsTable.setItems(Inventory.getAllParts());
                populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);

                int numKeyword = Integer.parseInt(keyword);
                Part part = Inventory.lookUpPart(numKeyword);

                if (part != null) {
                    partsTable.getSelectionModel().select(Inventory.getAllParts().indexOf(part));
                    partsTable.scrollTo(Inventory.getAllParts().indexOf(part));
                }
            }
            catch(NumberFormatException e){
                //ignore
            }
        }
        //If searching by name.
        else{
            partsTable.setItems(resultsList);
            populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);
            partsSearchBar.setText("");
        }

    }

    /** This is the productOnSearch method. This method handles searches. It returns product id or name matches.
     @param actionEvent the event to pass. */
    public void productOnSearchHandler(ActionEvent actionEvent) {

        productsTable.getSelectionModel().clearSelection();
        String keyword = productsSearchBar.getText();
        System.out.println(keyword);
        ObservableList<Product> resultsList = Inventory.lookUpProduct(keyword);

        //remove condition of list size if it can search by id and name together
        //If searching by id.
        if(resultsList.size() == 0){

            try {
                productsTable.setItems(Inventory.getAllProducts());
                populateTable(productsIdColumn, productsNameColumn, productsPriceColumn, productStockColumn, productMinColumn, productMaxColumn);

                int numKeyword = Integer.parseInt(keyword);
                Product product = Inventory.lookUpProduct(numKeyword);

                if (product != null) {
                    productsTable.getSelectionModel().select(Inventory.getAllProducts().indexOf(product));
                    productsTable.scrollTo(Inventory.getAllProducts().indexOf(product));
                }

            }
            catch(NumberFormatException e){
                //ignore
            }
        }
        //If searching by name.
        else{
            productsTable.setItems(resultsList);
            populateTable(productsIdColumn, productsNameColumn, productsPriceColumn, productStockColumn, productMinColumn, productMaxColumn);
            productsSearchBar.setText("");
        }

    }

}
