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

    private int index;

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

    public void cloneArray(){
        tempAssociatedParts.removeAll();
        for(int i = 0 ; i<InventoryController.getSelectedProduct().getAllAssociatedParts().size(); i++){
            tempAssociatedParts.add(InventoryController.getSelectedProduct().getAllAssociatedParts().get(i));
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


           newFullList.remove(part);

            //FOR MODIFY ONLY
            if(productPageTitle.getText() == "Modify Product"){
                Inventory.lookUpProduct(InventoryController.getSelectedProduct().getId()).addAssociatedPart(part);

            }
            else{
                //FOR ADD ONLY
                tempAssociatedParts.add(part);
            }


        }
    }

    public void onClickRemoveAssocPartBtn(ActionEvent actionEvent) {

        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if(part == null){
            return;
        }
        else{
            //FOR MODIFY ONLY
            if(productPageTitle.getText() == "Modify Product") {
                Inventory.lookUpProduct(InventoryController.getSelectedProduct().getId()).deleteAssociatedPart(part);
                System.out.println("remove part in product screen was clicked");
            }
            else {
                //FOR ADD ONLY
                tempAssociatedParts.remove(part);
            }
            newFullList.add(part);
        }
    }

    public void onClickSaveProductBtn(ActionEvent actionEvent) {
        System.out.println("Save in product screen was clicked");

        //MODIFY
        if(productPageTitle.getText() == "Modify Product"){
            //get index of selected Part
            int objIndex = Inventory.getAllProducts().indexOf(InventoryController.getSelectedProduct());
            //build a object with entered information
            Product tempProduct = new Product(Integer.parseInt(idProduct.getText()),nameProduct.getText(),Double.parseDouble(priceProduct.getText()),
                    Integer.parseInt(invProduct.getText()),Integer.parseInt(minProduct.getText()),Integer.parseInt(maxProduct.getText()),InventoryController.getSelectedProduct().getAllAssociatedParts());
            //call update product
            Inventory.updateProduct(objIndex,tempProduct);

        }
        else{
            //ADD PRODUCT
            System.out.println("Add product");
            Product productObj = new Product(index, nameProduct.getText(), Double.parseDouble(priceProduct.getText()), Integer.parseInt(invProduct.getText()), Integer.parseInt(minProduct.getText()), Integer.parseInt(maxProduct.getText()), tempAssociatedParts);
            Inventory.addProduct(productObj);
            tempAssociatedParts.removeAll();

        }

        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }

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

        resultsList.isEmpty();
    }
}
