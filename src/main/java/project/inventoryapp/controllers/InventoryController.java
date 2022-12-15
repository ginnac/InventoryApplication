package project.inventoryapp.controllers;
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
    public TableView productsTable;
    @FXML
    public TableColumn productsIdColumn;
    @FXML
    public TableColumn productsNameColumn;
    @FXML
    public TableColumn productsPriceColumn;
    @FXML
    public TableColumn productStockColumn;
    @FXML
    public TableColumn productMinColumn;
    @FXML
    public TableColumn productMaxColumn;
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

        partsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsMinColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
        partsMaxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
        //Test - FIX ME!!
        System.out.println(Inventory.getAllParts());

    }

    /** The on click method belows triggers the add parts screen in the App's inventory page. */
    public void onClickAddPartBtn(ActionEvent actionEvent) {

       // System.out.println("Add part button was clicked");
        /** Casting event source and determining where event source comes from. */
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();

        /** Then we can reference the proper fxml document. */
        try {
            scene = FXMLLoader.load(getClass().getResource("/project/inventoryapp/part.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onClickDeletePartBtn(ActionEvent actionEvent) {
        System.out.println("Delete part button was clicked");
    }

    public void onClickModifyPartBtn(ActionEvent actionEvent) {
        System.out.println("Modify part button was clicked");
    }

    public void onClickModifyProductBtn(ActionEvent actionEvent) {
        System.out.println("modify product button was clicked");
    }

    public void onClickAddProductBtn(ActionEvent actionEvent) {
        System.out.println("add product button was clicked");
    }

    public void onClickDeleteProductBtn(ActionEvent actionEvent) {
        System.out.println("delete product button was clicked");
    }
    public void onClickExitBtn(ActionEvent actionEvent) {

        System.exit(0);
        //System.out.println("exit button was clicked");
    }

}
