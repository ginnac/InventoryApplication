package project.inventoryapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static project.inventoryapp.controllers.InventoryController.pageLoader;

public class InHouseController implements Initializable {

    public ToggleGroup addPartsToggles;
    public TextField idBox;
    public TextField nameBox;
    public TextField invBox;
    public TextField priceBox;
    public TextField maxBox;
    public TextField minBox;
    public TextField machineIdBox;
    public Label inHouseScreenTitle;

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /** getting index to use for add part*/
        //get Title
        if(InventoryController.getPageTitle() == "Modify Part") {
            inHouseScreenTitle.setText(InventoryController.getPageTitle());

            //load part data in modify screen:
            idBox.setText(toString(InventoryController.getSelectedPart().getId()));
            nameBox.setText(InventoryController.getSelectedPart().getName());
            invBox.setText(toString(InventoryController.getSelectedPart().getStock()));
            priceBox.setText(toString(InventoryController.getSelectedPart().getPrice()));
            minBox.setText(toString(InventoryController.getSelectedPart().getMin()));
            maxBox.setText(toString(InventoryController.getSelectedPart().getMax()));
            machineIdBox.setText(InventoryController.getConditionalField());
        }
        else {
            //pull in index in add screen:
            index = Inventory.getAllParts().size() + 1;
            idBox.setText(toString(index));
        }
    }

    private String toString(int index) {
        return "" + index + "";
    }
    private String toString(double index) {
        return "" + index + "";
    }


    public void onSelectOutsourcedToggle(ActionEvent actionEvent) {
      pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml", "radioButton");
    }

    public void inHouseOnClickSaveBtn(ActionEvent actionEvent) {

        if(InventoryController.getPageTitle() == "Modify Part"){
            //get id from screen
            int idToSave = Integer.parseInt(idBox.getText());
            //find object with that id in parts list. First loop
            for(int i = 0; i<Inventory.getAllParts().size(); i++){
                //then compare id in UI vs Part's id in Parts list. If they match update all values with the exception of Id.
                if(idToSave == Inventory.getAllParts().get(i).getId()){
                    Inventory.getAllParts().get(i).setName(nameBox.getText());
                    Inventory.getAllParts().get(i).setStock(Integer.parseInt(invBox.getText()));
                    Inventory.getAllParts().get(i).setPrice(Double.parseDouble(priceBox.getText()));
                    Inventory.getAllParts().get(i).setMin(Integer.parseInt(minBox.getText()));
                    Inventory.getAllParts().get(i).setMax(Integer.parseInt(maxBox.getText()));
                    ((InHouse)Inventory.getAllParts().get(i)).setMachineId(Integer.parseInt(machineIdBox.getText()));
                }
            }
            // redirect to inventory screen
            pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");

        }
        else {
            InHouse inHouseObj = new InHouse(index, nameBox.getText(), Double.parseDouble(priceBox.getText()), Integer.parseInt(invBox.getText()), Integer.parseInt(minBox.getText()), Integer.parseInt(maxBox.getText()), Integer.parseInt(machineIdBox.getText()));
            Inventory.addPart(inHouseObj);
            pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
        }
    }

    public void InHouseOnClickCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
