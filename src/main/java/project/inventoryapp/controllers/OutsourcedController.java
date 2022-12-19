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
    public Label errorMessage;

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
        try {
            // check that fields are not blank and that integer boxes receive integer values. Push that to error list in the part class:
            PartController.checkForErrors(outsourcedNameBox.getText(),"string", "Name");
            PartController.checkForErrors(outsourcedInvBox.getText(),"integer", "Inv");
            PartController.checkForErrors(outsourcedPriceBox.getText(),"double", "Price/Cost");
            PartController.checkForErrors(outsourcedMaxBox.getText(),"integer", "Max");
            PartController.checkForErrors(outsourcedMinBox.getText(),"integer", "Min");
            PartController.checkForErrors(outsourcedCompanyName.getText(),"string", "Company Name");
            //if loading Modify Parts screen:
            if (InventoryController.getPageTitle() == "Modify Part") {
                //get id from screen
                int objIndex = Inventory.getAllParts().indexOf(InventoryController.getSelectedPart());
                //build a object with entered information
                Part tempPart = new Outsourced(Integer.parseInt(outsourcedIdBox.getText()), outsourcedNameBox.getText(),
                        Double.parseDouble(outsourcedPriceBox.getText()), Integer.parseInt(outsourcedInvBox.getText()), Integer.parseInt(outsourcedMinBox.getText()),
                        Integer.parseInt(outsourcedMaxBox.getText()), outsourcedCompanyName.getText());
                //call update part
                Inventory.updatePart(objIndex, tempPart);
            }
            //if loading add parts screen:
            else {
                Outsourced outsourcedObj = new Outsourced(index, outsourcedNameBox.getText(), Double.parseDouble(outsourcedPriceBox.getText()), Integer.parseInt(outsourcedInvBox.getText()), Integer.parseInt(outsourcedMinBox.getText()), Integer.parseInt(outsourcedMaxBox.getText()), outsourcedCompanyName.getText());
                Inventory.addPart(outsourcedObj);
            }
            // redirect to inventory screen
            pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
        }
        catch (NumberFormatException e){
            String errMsg = "Please enter valid values:" ;
            //Render the parts error list below
            for (String str : PartController.getPartErrorList()){
                //System.out.println(str);
                errMsg += "\n" + "-" + str;
            }
            errorMessage.setText(errMsg);
            PartController.emptyErrorList();
        }

    }

    public void onClickOutsourcedCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
