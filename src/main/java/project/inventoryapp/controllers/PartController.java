package project.inventoryapp.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the PartController class. This class handles the part fxml file. */
public class PartController implements Initializable {

    public ToggleGroup addPartsToggles;
    Stage stage;
    Parent scene;

    private static ObservableList<String> partErrorList = FXCollections.observableArrayList();

    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /** This is the loader method. This method loads the different pages based on the buttons clicked on. */
    public void partPageLoader(ActionEvent actionEvent, String path){
        //Casting event source and determining where event source comes from.
        stage = (Stage)((RadioButton)actionEvent.getSource()).getScene().getWindow();

        //Then we can reference the proper fxml document.
        try {
            scene = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the onSelectInHouse method. This method triggers the loader method to take the user to the inHouse page.
     @param actionEvent event is passed. */
    public void onSelectInHousePart(ActionEvent actionEvent) {
        partPageLoader(actionEvent, "/project/inventoryapp/inhouse.fxml");
    }

    /** This is the onSelectOutsourced method. This method triggers the loader method to take the user to the outsourced page.
     @param actionEvent event is passed. */
    public void onSelectOutsourcedPart(ActionEvent actionEvent) {
        partPageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml");
    }

    /** This is the checkForErrors method. This methods handles Exception errors for the parts add/modify screens.
     @param value value entered.
     @param type type of value.
     @param field name of the field the value was entered in. */
    public static void checkForErrors(String value, String type, String field){

        if(((type =="integer") || (type == "double")) && (value != "") ){
            //check that is not null and is numerical
            try{
                if(type =="integer") {
                    Integer.parseInt(value);
                }
                else{
                    Double.parseDouble(value);
                }
            }
            catch (NumberFormatException e){
                partErrorList.add(field + " has to be a numerical value.");
            }
        }
            //check that os not blank
        if(value == ""){
            partErrorList.add(field + " can not be blank.");
        }

    }

    /** This is the getPartErrorList method. It gets the list of errors collected.
     @return partErrorList the list of errors collected. */
    public static ObservableList<String> getPartErrorList() {
        return partErrorList;
    }

    /** This is the emptyErrorList method. It clears the list of Errors. */
    public static void emptyErrorList(){
        partErrorList.clear();
    }
}
