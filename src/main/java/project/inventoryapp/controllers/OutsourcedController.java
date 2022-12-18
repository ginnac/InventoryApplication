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

public class OutsourcedController implements Initializable{

    public ToggleGroup addPartsToggles;
    public TextField outsourcedIdBox;
    public TextField outsourcedNameBox;
    public TextField outsourcedInvBox;
    public TextField outsourcedPriceBox;
    public TextField outsourcedMaxBox;
    public TextField outsourcedMinBox;
    public TextField outsourcedCompanyName;
    public Label outsourcedScreenTitle;

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(InventoryController.getPageTitle() == "Modify Part") {
            outsourcedScreenTitle.setText(InventoryController.getPageTitle());

            //load part data in modify screen:
            outsourcedIdBox.setText(toString(InventoryController.getSelectedPart().getId()));
            outsourcedNameBox.setText(InventoryController.getSelectedPart().getName());
            outsourcedInvBox.setText(toString(InventoryController.getSelectedPart().getStock()));
            outsourcedPriceBox.setText(toString(InventoryController.getSelectedPart().getPrice()));
            outsourcedMinBox.setText(toString(InventoryController.getSelectedPart().getMin()));
            outsourcedMaxBox.setText(toString(InventoryController.getSelectedPart().getMax()));
            outsourcedCompanyName.setText(InventoryController.getConditionalField());
        }
        else {
            //pull in index in add screen:
            index = Inventory.getAllParts().size() + 1;
            outsourcedIdBox.setText(toString(index));
        }

    }

    private String toString(int index) {
        return "" + index + "";
    }

    private String toString(double index) {
        return "" + index + "";
    }

    public void onSelectedInHouseToggle(ActionEvent actionEvent) {
        pageLoader(actionEvent,"/project/inventoryapp/inhouse.fxml", "radioButton" );
    }

    public void onClickOutsourcedSaveBtn(ActionEvent actionEvent) {

        //if loading Modify Parts screen:
        if(InventoryController.getPageTitle() == "Modify Part"){
            //get id from screen
            int objIndex = Inventory.getAllParts().indexOf(InventoryController.getSelectedPart());
            //build a object with entered information
            Part tempPart = new Outsourced(Integer.parseInt(outsourcedIdBox.getText()),outsourcedNameBox.getText(),
                    Double.parseDouble(outsourcedPriceBox.getText()),Integer.parseInt(outsourcedInvBox.getText()),Integer.parseInt(outsourcedMinBox.getText()),
                    Integer.parseInt(outsourcedMaxBox.getText()),outsourcedCompanyName.getText());
            //call update part
            Inventory.updatePart(objIndex,tempPart);
        }
        //if loading add parts screen:
        else {
            Outsourced outsourcedObj = new Outsourced(index, outsourcedNameBox.getText(), Double.parseDouble(outsourcedPriceBox.getText()), Integer.parseInt(outsourcedInvBox.getText()), Integer.parseInt(outsourcedMinBox.getText()), Integer.parseInt(outsourcedMaxBox.getText()), outsourcedCompanyName.getText());
            Inventory.addPart(outsourcedObj);
        }
        // redirect to inventory screen
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }

    public void onClickOutsourcedCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
