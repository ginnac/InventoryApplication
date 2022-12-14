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
}