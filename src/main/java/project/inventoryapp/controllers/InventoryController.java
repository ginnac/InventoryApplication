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

public class InventoryController  implements Initializable{

    /** Reference variables of containers. */
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
    public static String getPageTitle() {
        return pageTitle;
    }

    private static Part selectedPart;
    public static Part getSelectedPart() {
        return selectedPart;
    }

    private static String conditionalField;
    public static String getConditionalField() {
        return conditionalField;
    }


    //public int idColumn;
    /** Initializable method loads up as soon as the associated page loads up. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I was initialized");

        // 3 Test data items below -  FIX ME AS NEEDED:
        partsTable.setItems(Inventory.getAllParts());
        populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);

        productsTable.setItems(Inventory.getAllProducts());
        populateTable(productsIdColumn, productsNameColumn, productsPriceColumn, productStockColumn, productMinColumn, productMaxColumn);


    }

    public void populateTable(TableColumn<?, Integer> id, TableColumn<?, String> name, TableColumn<?, Double> price, TableColumn<?, Integer> stock, TableColumn<?, Integer> min, TableColumn<?, Integer> max){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        min.setCellValueFactory(new PropertyValueFactory<>("min"));
        max.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

    /** The on click method belows triggers the add parts screen in the App's inventory page. */

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


    /** Method to add parts to parts list*/
    public void onClickAddPartBtn(ActionEvent actionEvent) {
        pageTitle = "Add Part";
        pageLoader(actionEvent, "/project/inventoryapp/part.fxml", "button");

    }

    public void onClickDeletePartBtn(ActionEvent actionEvent) {
        System.out.println("Delete part button was clicked");

        Part part = partsTable.getSelectionModel().getSelectedItem();
        try {
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
                System.out.println("Cancelled was clicked");
                partsTable.getSelectionModel().clearSelection();
                //pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
            }
        }
         catch(Exception e){
                //test
        }
    }


    public void onClickModifyPartBtn(ActionEvent actionEvent) {

        System.out.println("Modify part button was clicked");
        pageTitle = "Modify Part";

        //grab selected part
        Part part = partsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            return;
        }
        else{
            //get selected ID
            System.out.println(part.getId());
        }

        //Store object as static value
        selectedPart = part;


        //load proper page based on instance type
        if(part instanceof InHouse){
            conditionalField = String.valueOf(((InHouse) part).getMachineId());
            pageLoader(actionEvent, "/project/inventoryapp/inhouse.fxml", "button");
        }
        else{
            conditionalField = ((Outsourced)part).getCompanyName();
            pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml", "button");
        }

    }

    public void onClickModifyProductBtn(ActionEvent actionEvent) {
        System.out.println("modify product button was clicked");
    }

    public void onClickAddProductBtn(ActionEvent actionEvent) {

        System.out.println("add product button was clicked");
        pageLoader(actionEvent, "/project/inventoryapp/product.fxml", "button");

    }

    public void onClickDeleteProductBtn(ActionEvent actionEvent) {
        System.out.println("delete product button was clicked");
    }
    public void onClickExitBtn(ActionEvent actionEvent) {

        System.exit(0);
        //System.out.println("exit button was clicked");
    }

    public void partOnSearchHandler(ActionEvent actionEvent) {

        partsTable.getSelectionModel().clearSelection();
        String keyword = partsSearchBar.getText();
        ObservableList<Part> resultsList = Inventory.lookUpPart(keyword);

        //remove condition of list size if it can search by id and name together
        //If searching by id.
        if(resultsList.size() == 0){

            try {
                partsTable.setItems(Inventory.getAllParts());
                populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);

                int numKeyword = Integer.parseInt(keyword);
                Part part = Inventory.lookUpPart(numKeyword);

                if (part != null) {
                    //resultsList.add(part);
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

        resultsList.isEmpty();
    }

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

        resultsList.isEmpty();

    }

}
