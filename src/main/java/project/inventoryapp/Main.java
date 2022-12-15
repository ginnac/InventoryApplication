package project.inventoryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.inventoryapp.controllers.InHouse;
import project.inventoryapp.controllers.Inventory;
import project.inventoryapp.controllers.Outsourced;

import java.io.IOException;

/** This class creates an app that manages inventory.*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1105, 424);
        stage.setTitle("C482 Project");
        stage.setScene(scene);
        stage.show();
    }
    /** This is the main method. This is the first method that gets called for this program. */
    public static void main(String[] args) {

        //TEST DATA:
        InHouse inHousePart = new InHouse(1, "Tire", 100.00, 20, 10, 40, 10);
        Inventory.addPart(inHousePart);

        Outsourced outsourcedPart = new Outsourced(2, "Break", 250.00, 10, 5, 20, "carpartscom");
        Inventory.addPart(outsourcedPart);


        launch();
    }

    //FIX ME! - remove it, javadoc comment below only for testing
    /** This method displays a message.
     The message is a greeting.
     @param message The first message
     @return Returns Hello
     */
    public static String displayMessage(String message){
        //FIX ME - Remove this method
        return "Hello";
    }
}