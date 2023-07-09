package com.example.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DijkstraProjectApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        HelloController controller = loader.getController();

        // Load buildings data and initialize the controller
        DijkstraProject dijkstraProject = new DijkstraProject();
        dijkstraProject.readBuildingsFromFile("C:/Users/Tala Dabbagh/OneDrive/Desktop/Algo 2/proj3/project3/src/main/resources/com/example/project3/ToRead.txt");
        controller.initialize(dijkstraProject);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
