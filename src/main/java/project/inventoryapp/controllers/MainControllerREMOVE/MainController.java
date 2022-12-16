package project.inventoryapp.controllers.MainControllerREMOVE;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Label buttonLabel;
    public int counter = 1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I was initialized");
    }

    public void OnButtonClicked(ActionEvent actionEvent) {
        System.out.println("I was clicked!!");
        buttonLabel.setText("you clicked " + counter++ + " times");
    }

    //Test Different DIALOG BOXES!!
    //Error. Usually used in the try-catch block in the catch section to return an error in the UI
        /*
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Error dialog");
        alert.setContentText("You are not allowed to delete - TESTING ERROR");
        alert.showAndWait();
        */
    //Warning. Just to make user aware of something
       /*
        Alert alert = new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Warning dialog");
        alert.setContentText("Next data to render is confidential- TESTING WARNING");
        alert.showAndWait();
       */


    //LOOP OPTIONS
    /*
    //enhanced loop option
    for(BasketballPlayer bp: allPlayers){
    //check if bp meets criteria
    }
    * */
}