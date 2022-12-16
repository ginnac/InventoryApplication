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
    Stage stage;
    Parent scene;
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

    /** Method to add parts to parts list*/
    public void onClickAddPartBtn(ActionEvent actionEvent) {

       pageLoader(actionEvent, "/project/inventoryapp/part.fxml", "button");

    }

    public void onClickDeletePartBtn(ActionEvent actionEvent) {
        System.out.println("Delete part button was clicked");

        Alert alert = new Alert (Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part item?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Ok was clicked");

            //FIX ME!!!! - Change this so it can take any id number
            if(Inventory.lookUpPart(1) != null){
                Inventory.deletePart(Inventory.lookUpPart(1) );
            }
            else{
                System.out.println("Part not found");
            }

            pageLoader(actionEvent,"/project/inventoryapp/inventory.fxml", "button");

        }
        else{
            System.out.println("Cancelled was clicked");
            pageLoader(actionEvent,"/project/inventoryapp/inventory.fxml", "button");
        }
    }

    public void onClickModifyPartBtn(ActionEvent actionEvent) {
        System.out.println("Modify part button was clicked");
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

        String keyword = partsSearchBar.getText();
        ObservableList<Part> resultsList = Inventory.lookUpPart(keyword);

        //remove condition of list size if it can search by id and name together
        if(resultsList.size() == 0){

            try {
                int numKeyword = Integer.parseInt(keyword);
                Part part = Inventory.lookUpPart(numKeyword);

                if (part != null) {
                    resultsList.add(part);
                }
            }
            catch(NumberFormatException e){
                //ignore
            }
        }

        partsTable.setItems(resultsList);
        populateTable(partsIdColumn, partsNameColumn, partsPriceColumn, partsStockColumn, partsMinColumn, partsMaxColumn);
        partsSearchBar.setText("");
    }

    public void productOnSearchHandler(ActionEvent actionEvent) {
    }
}
