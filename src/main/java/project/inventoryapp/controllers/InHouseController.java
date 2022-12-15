package project.inventoryapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InHouseController implements Initializable {
    public ToggleGroup addPartsToggles;
    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void pageLoader(ActionEvent actionEvent, String path){
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

    public void onSelectOutsourcedToggle(ActionEvent actionEvent) {
        pageLoader(actionEvent, "/project/inventoryapp/outsourced.fxml");
    }
}
