package project.inventoryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.inventoryapp.controllers.InHouse;
import project.inventoryapp.controllers.Inventory;
import project.inventoryapp.controllers.Outsourced;
import project.inventoryapp.controllers.Product;

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

        //TEST DATA - Parts:
        InHouse inHousePart = new InHouse(1, "Tire", 100.00, 20, 10, 40, 10);
        Outsourced outsourcedPart = new Outsourced(2, "Break", 250.00, 10, 5, 20, "carpartscom");

        Inventory.addPart(inHousePart);
        Inventory.addPart(outsourcedPart);

        //TEST DATA - Products:
        Product product1 = new Product(1, "Unicycle" , 200.00, 10, 2, 15);
        Product product2 = new Product(1, "Mountain Bike" , 350.00, 15, 5, 40);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);

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