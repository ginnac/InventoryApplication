module project.inventoryapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.inventoryapp to javafx.fxml;
    exports project.inventoryapp;
    exports project.inventoryapp.controllers;
    opens project.inventoryapp.controllers to javafx.fxml;

}