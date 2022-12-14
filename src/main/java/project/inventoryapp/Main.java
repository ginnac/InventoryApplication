package project.inventoryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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