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
import java.util.ResourceBundle;

import static project.inventoryapp.controllers.InventoryController.pageLoader;

public class ProductController implements Initializable {

    Stage stage;
    Parent scene;
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

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newFullList.addAll(Inventory.getAllParts());

        //Render when adding a Part or Modifying a Part
        allPartsTable.setItems(newFullList);
        populateTable(allPartsIdCol, allPartsNameCol, allPartsPriceCol, allPartsInvCol, allPartsMinCol, allPartsMaxCol);

        if(InventoryController.getPageTitle() == "Modify Product") {

            //associated Parts will render all associated parts. should render if they are modifying a product otherwise it should be empty. MODIFY ONLY.
            associatedPartsTable.setItems(Inventory.lookUpProduct(1).getAllAssociatedParts());
            populateTable(assocPartsIdCol, assocPartsNameCol, assocPartsPriceCol, assocPartsInvCol, assocPartsMinCol, assocPartsMaxCol);

        }
        else{
            //ADD PRODUCT ONLY
            index = Inventory.getAllProducts().size() + 1;
            idProduct.setText(toString(index));
            associatedPartsTable.setItems(tempAssociatedParts);
            populateTable(assocPartsIdCol, assocPartsNameCol, assocPartsPriceCol, assocPartsInvCol, assocPartsMinCol, assocPartsMaxCol);

        }

    }

    private String toString(int index) {
        return "" + index + "";
    }
    private String toString(double index) {
        return "" + index + "";
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
            //FOR ADD ONLY
            tempAssociatedParts.add(part);
            //FOR MODIFY ONLY
            //Inventory.lookUpProduct(1).addAssociatedPart(part);
        }
    }

    public void onClickRemoveAssocPartBtn(ActionEvent actionEvent) {
        System.out.println("remove part in product screen was clicked");

        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if(part == null){
            return;
        }
        else{
            Inventory.lookUpProduct(part.getId()).deleteAssociatedPart(part);
            newFullList.add(part);
        }
    }

    public void onClickSaveProductBtn(ActionEvent actionEvent) {
        System.out.println("Save in product screen was clicked");

        //ADD PRODUCT
        Product productObj = new Product(index, nameProduct.getText(), Double.parseDouble(priceProduct.getText()), Integer.parseInt(invProduct.getText()), Integer.parseInt(minProduct.getText()), Integer.parseInt(maxProduct.getText()), tempAssociatedParts);
        Inventory.addProduct(productObj);
        tempAssociatedParts.removeAll();
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }

    public void onClickProductCancelBtn(ActionEvent actionEvent) {
        System.out.println("Cancel in product screen was clicked");
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
