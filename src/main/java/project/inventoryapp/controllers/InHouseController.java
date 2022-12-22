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

/**This is the InHouseController class. This class handles methods for the inHouse fxml file. */
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
    public Label errorMessage;

    private int index;

    /** This is the initialize method. It loads values for the inHouse view/fxml file.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //For Modify screen
        if(InventoryController.getPageTitle() == "Modify Part") {
            inHouseScreenTitle.setText(InventoryController.getPageTitle());

            idBox.setText(toString(InventoryController.getSelectedPart().getId()));
            nameBox.setText(InventoryController.getSelectedPart().getName());
            invBox.setText(toString(InventoryController.getSelectedPart().getStock()));
            priceBox.setText(toString(InventoryController.getSelectedPart().getPrice()));
            minBox.setText(toString(InventoryController.getSelectedPart().getMin()));
            maxBox.setText(toString(InventoryController.getSelectedPart().getMax()));
            machineIdBox.setText(InventoryController.getConditionalField());
        }
        //For Add screen
        else {
            index = Inventory.getAllParts().size() + 1;
            idBox.setText(toString(index));
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

    /** This onSelectOutsourced method is used to load the outsourced page.
     @param actionEvent event to pass. */
    public void onSelectOutsourcedToggle(ActionEvent actionEvent) {
      pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml", "radioButton");
    }

    /** This save method is used to save a new inHouse part or changes on a modified inHouse part.
     @param actionEvent event to pass. */
    public void inHouseOnClickSaveBtn(ActionEvent actionEvent) {
        //clear error message field if trying to save again
        errorMessage.setText("");
        try {
            // check that fields are not blank and that integer boxes receive integer values. Push that to error list in the part class:
            PartController.checkForErrors(nameBox.getText(),"string", "Name");
            PartController.checkForErrors(invBox.getText(),"integer", "Inv");
            PartController.checkForErrors(priceBox.getText(),"double", "Price/Cost");
            PartController.checkForErrors(maxBox.getText(),"integer", "Max");
            PartController.checkForErrors(minBox.getText(),"integer", "Min");
            PartController.checkForErrors(machineIdBox.getText(),"integer", "Machine ID");

            if((Integer.parseInt(minBox.getText()) <= Integer.parseInt(maxBox.getText())) && (Integer.parseInt(invBox.getText()) >= Integer.parseInt(minBox.getText()))
                    && (Integer.parseInt(invBox.getText()) <= Integer.parseInt(maxBox.getText())) ) {
                //Modify Part
                if (InventoryController.getPageTitle() == "Modify Part") {
                    //get index of selected Part
                    int objIndex = Inventory.getAllParts().indexOf(InventoryController.getSelectedPart());
                    //build a object with entered information
                    Part tempPart = new InHouse(Integer.parseInt(idBox.getText()), nameBox.getText(),
                            Double.parseDouble(priceBox.getText()), Integer.parseInt(invBox.getText()), Integer.parseInt(minBox.getText()),
                            Integer.parseInt(maxBox.getText()), Integer.parseInt(machineIdBox.getText()));
                    //call update part
                    Inventory.updatePart(objIndex, tempPart);
                }
                //Add a Part
                else {
                    InHouse inHouseObj = new InHouse(index, nameBox.getText(), Double.parseDouble(priceBox.getText()), Integer.parseInt(invBox.getText()), Integer.parseInt(minBox.getText()), Integer.parseInt(maxBox.getText()), Integer.parseInt(machineIdBox.getText()));
                    Inventory.addPart(inHouseObj);
                }
                // redirect to inventory screen
                pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
            }
            else{
                String errMsg = "Please enter valid values:";
                if(Integer.parseInt(minBox.getText()) > Integer.parseInt(maxBox.getText())){
                    errMsg += "\n" + "-" + "Max must be greater or equal than Min.";
                    errorMessage.setText(errMsg);
                }
                if((Integer.parseInt(minBox.getText()) > Integer.parseInt(invBox.getText())) || (Integer.parseInt(maxBox.getText()) < Integer.parseInt(invBox.getText())) ){
                    errMsg += "\n" + "-" + "Inv must between Min and Max ";
                    errorMessage.setText(errMsg);
                }
            }
        }
        catch (NumberFormatException e){
            //System.out.println("Please enter valid values in the fields:");
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

    /** This cancel method is used to cancel changes within inHouse add/modify screens. It also loads the inventory page.
     @param actionEvent event to pass. */
    public void InHouseOnClickCancelBtn(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/inventory.fxml", "button");
    }
}
