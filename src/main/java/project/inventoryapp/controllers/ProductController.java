package project.inventoryapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    Stage stage;
    Parent scene;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newFullList.addAll(Inventory.getAllParts());
        allPartsTable.setItems(newFullList);
        associatedPartsTable.setItems(Inventory.lookUpProduct(1).getAllAssociatedParts());

        populateTable(allPartsIdCol, allPartsNameCol, allPartsPriceCol, allPartsInvCol, allPartsMinCol, allPartsMaxCol);
        populateTable(assocPartsIdCol, assocPartsNameCol, assocPartsPriceCol, assocPartsInvCol, assocPartsMinCol, assocPartsMaxCol);

    }

    public void populateTable(TableColumn<?, Integer> id, TableColumn<?, String> name, TableColumn<?, Double> price, TableColumn<?, Integer> stock, TableColumn<?, Integer> min, TableColumn<?, Integer> max){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        min.setCellValueFactory(new PropertyValueFactory<>("min"));
        max.setCellValueFactory(new PropertyValueFactory<>("max"));
    }

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

    public void onClickAddAssocPartBtn(ActionEvent actionEvent) {
        System.out.println("Add part in product screen was clicked");

        Part part = allPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
           return;
        }
        else{
            //Inventory.deletePart(part);
            newFullList.remove(part);
            Inventory.lookUpProduct(1).addAssociatedPart(part);
        }
    }

    public void onClickRemoveAssocPartBtn(ActionEvent actionEvent) {
        System.out.println("remove part in product screen was clicked");

        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if(part == null){
            return;
        }
        else{
            Inventory.lookUpProduct(1).deleteAssociatedPart(part);
            newFullList.add(part);
        }
    }

    public void onClickSaveProductBtn(ActionEvent actionEvent) {
        System.out.println("Save in product screen was clicked");
    }

    public void onClickProductCancelBtn(ActionEvent actionEvent) {
        System.out.println("Cancel in product screen was clicked");
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
