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

/** This is the OutsourcedController class. This class handles methods for the outsourced fxml file. */
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

    /** This is the initialize method. It loads values for the inHouse view/fxml file.*/
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

    /** This toString method is used to cast an integer to String.
     @param index index to cast.
     @return index in string form. */
    private String toString(int index) {
        return "" + index + "";
    }

    /** This toString method is used to cast a double to String.
     @param index index to cast.
     @return index in string form. */
    private String toString(double index) {
        return "" + index + "";
    }

    /** This onSelectInHouse method is used to load the inHouse page.
     @param actionEvent event to pass. */
    public void onSelectedInHouseToggle(ActionEvent actionEvent) {
        pageLoader(actionEvent,"/project/inventoryapp/inhouse.fxml", "radioButton" );
    }

    /** This save method is used to save a new outsourced part or changes on a modified outsourced part.
     @param actionEvent event to pass. */
    public void onClickOutsourcedSaveBtn(ActionEvent actionEvent) {
        //clear error message field if trying to save again
        errorMessage.setText("");
        try {
            // check that fields are not blank and that integer boxes receive integer values. Push that to error list in the part class:
            PartController.checkForErrors(outsourcedNameBox.getText(), "string", "Name");
            PartController.checkForErrors(outsourcedInvBox.getText(), "integer", "Inv");
            PartController.checkForErrors(outsourcedPriceBox.getText(), "double", "Price/Cost");
            PartController.checkForErrors(outsourcedMaxBox.getText(), "integer", "Max");
            PartController.checkForErrors(outsourcedMinBox.getText(), "integer", "Min");
            PartController.checkForErrors(outsourcedCompanyName.getText(), "string", "Company Name");

            if ((Integer.parseInt(outsourcedMinBox.getText()) <= Integer.parseInt(outsourcedMaxBox.getText())) && (Integer.parseInt(outsourcedInvBox.getText()) >= Integer.parseInt(outsourcedMinBox.getText()))
                    && (Integer.parseInt(outsourcedInvBox.getText()) <= Integer.parseInt(outsourcedMaxBox.getText()))) {
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
            else{
                String errMsg = "Please enter valid values:";
                if(Integer.parseInt(outsourcedMinBox.getText()) > Integer.parseInt(outsourcedMaxBox.getText())){
                    errMsg += "\n" + "-" + "Max must be greater or equal than Min.";
                    errorMessage.setText(errMsg);
                }
                if((Integer.parseInt(outsourcedMinBox.getText()) > Integer.parseInt(outsourcedInvBox.getText())) || (Integer.parseInt(outsourcedMaxBox.getText()) < Integer.parseInt(outsourcedInvBox.getText())) ){
                    errMsg += "\n" + "-" + "Inv must between Min and Max ";
                    errorMessage.setText(errMsg);
                }
            }
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


    /** This cancel method is used to cancel changes within outsourced add/modify screens. It also loads the inventory page.
     @param actionEvent event to pass. */
    public void onClickOutsourcedCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
