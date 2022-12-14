package project.inventoryapp.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import project.inventoryapp.models.InHouse;
import project.inventoryapp.models.Inventory;
import project.inventoryapp.models.Outsourced;


import java.net.URL;
import java.util.ResourceBundle;


public class InventoryController  implements Initializable{

    public TableView partsTable;
    //public int idColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I was initialized");

        // 3 Test data items below -  FIX ME AS NEEDED:
        InHouse inHousePart = new InHouse(1, "Tire", 100.00, 20, 10, 40, 10);
        Inventory.addPart(inHousePart);

        Outsourced outsourcedPart = new Outsourced(2, "Break", 250.00, 10, 5, 20, "carpartsdotcom");
        Inventory.addPart(outsourcedPart);

        partsTable.setItems(Inventory.getAllParts());
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Test - FIX ME!!
        System.out.println(Inventory.getAllParts());


    }


}
