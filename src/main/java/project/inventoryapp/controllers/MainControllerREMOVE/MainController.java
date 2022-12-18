package project.inventoryapp.controllers.MainControllerREMOVE;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
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


    /*
    for(int i = 0; i<Inventory.getAllParts().size(); i++){
                //then compare id in UI vs Part's id in Parts list. If they match update all values with the exception of Id.
                if(idToSave == Inventory.getAllParts().get(i).getId()){
                    Inventory.getAllParts().get(i).setName(nameBox.getText());
                    Inventory.getAllParts().get(i).setStock(Integer.parseInt(invBox.getText()));
                    Inventory.getAllParts().get(i).setPrice(Double.parseDouble(priceBox.getText()));
                    Inventory.getAllParts().get(i).setMin(Integer.parseInt(minBox.getText()));
                    Inventory.getAllParts().get(i).setMax(Integer.parseInt(maxBox.getText()));
                    ((InHouse)Inventory.getAllParts().get(i)).setMachineId(Integer.parseInt(machineIdBox.getText()));
                }
            }
     */

    /*
    //find object with that id in parts list. First loop
            for(int i = 0; i<Inventory.getAllProducts().size(); i++){

                //then compare id in UI vs Part's id in Parts list. If they match update all values with the exception of Id.
                if(idToSave == Inventory.getAllProducts().get(i).getId()){
                    Inventory.getAllProducts().get(i).setName(nameProduct.getText());
                    Inventory.getAllProducts().get(i).setStock(Integer.parseInt(invProduct.getText()));
                    Inventory.getAllProducts().get(i).setPrice(Double.parseDouble(priceProduct.getText()));
                    Inventory.getAllProducts().get(i).setMin(Integer.parseInt(minProduct.getText()));
                    Inventory.getAllProducts().get(i).setMax(Integer.parseInt(maxProduct.getText()));


                }
            }
    */


    //Page Loader:
    /*

    Stage stage;
    Parent scene;

    public void pageLoader(ActionEvent actionEvent, String path, String elementType){
       //Casting event source and determining where event source comes from.
        if (elementType == "radioButton"){
            stage = (Stage)((RadioButton)actionEvent.getSource()).getScene().getWindow();
        }
        else{
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        }

        //Then we can reference the proper fxml document.
        try {
            scene = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(new Scene(scene));
        stage.show();
    }
    */
}

