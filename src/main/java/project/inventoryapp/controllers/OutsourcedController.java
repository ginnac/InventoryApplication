package project.inventoryapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        index = Inventory.getAllParts().size() + 1;
        outsourcedIdBox.setText(toString(index));
    }

    private String toString(int index) {
        return "" + index + "";
    }



    public void onSelectedInHouseToggle(ActionEvent actionEvent) {
        pageLoader(actionEvent,"/project/inventoryapp/inhouse.fxml", "radioButton" );
    }


    public void onClickOutsourcedSaveBtn(ActionEvent actionEvent) {

        Outsourced outsourcedObj = new Outsourced(index, outsourcedNameBox.getText(), Double.parseDouble(outsourcedPriceBox.getText()), Integer.parseInt(outsourcedInvBox.getText()), Integer.parseInt(outsourcedMinBox.getText()),Integer.parseInt(outsourcedMaxBox.getText()), outsourcedCompanyName.getText());
        Inventory.addPart(outsourcedObj);
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }

    public void onClickOutsourcedCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
