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

public class InHouseController implements Initializable {

    Stage stage;
    Parent scene;
    public ToggleGroup addPartsToggles;
    public TextField idBox;
    public TextField nameBox;
    public TextField invBox;
    public TextField priceBox;
    public TextField maxBox;
    public TextField minBox;
    public TextField machineIdBox;

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /** getting index to use for add part*/
       index = Inventory.getAllParts().size() + 1;
       idBox.setText(toString(index));

    }

    private String toString(int index) {
        return "" + index + "";
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

    public void onSelectOutsourcedToggle(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml", "radioButton");
    }

    public void inHouseOnClickSaveBtn(ActionEvent actionEvent) {

        InHouse inHouseObj = new InHouse(index, nameBox.getText(), Double.parseDouble(priceBox.getText()), Integer.parseInt(invBox.getText()), Integer.parseInt(minBox.getText()),Integer.parseInt(maxBox.getText()), Integer.parseInt(machineIdBox.getText()));
        Inventory.addPart(inHouseObj);
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }

    public void InHouseOnClickCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
