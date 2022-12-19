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

public class PartController implements Initializable {

    public ToggleGroup addPartsToggles;
    Stage stage;
    Parent scene;

    private static ObservableList<String> partErrorList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void partPageLoader(ActionEvent actionEvent, String path){
        /** Casting event source and determining where event source comes from. */
        stage = (Stage)((RadioButton)actionEvent.getSource()).getScene().getWindow();

        /** Then we can reference the proper fxml document. */
        try {
            scene = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onSelectInHousePart(ActionEvent actionEvent) {
        partPageLoader(actionEvent, "/project/inventoryapp/inhouse.fxml");
    }

    public void onSelectOutsourcedPart(ActionEvent actionEvent) {
        partPageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml");
    }

    //errors getter and setters

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


    public static ObservableList<String> getPartErrorList() {
        return partErrorList;
    }

    public static void emptyErrorList(){
        partErrorList.clear();
    }
}
