package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

public class HelloController {
    @FXML
    private Button runB;
    @FXML
    private ImageView imageV;

    @FXML
    private Pane imgPane;

    @FXML
    private ComboBox<String> comboS;

    @FXML
    private ComboBox<String> comboT;

    @FXML
    private TextArea pathTA;

    @FXML
    private TextArea distTA;
    @FXML
    private Button resetBtn;

    private DijkstraProject dijkstraProject;

    public void initialize(DijkstraProject project) {
        dijkstraProject = project;

        List<String> buildingNames = dijkstraProject.getBuildingNames();
        comboS.getItems().addAll(buildingNames);
        comboT.getItems().addAll(buildingNames);

        drawDotsOnImageView();
        resetBtn.setOnAction(event -> reset());


//        imgPane.setOnMouseClicked(event -> {
//            double x = event.getX(); // the x-coordinate of the dot
//            double y = event.getY(); // the y-coordinate of the dot
//
//            Circle circle = new Circle(x, y, 4, Color.RED);
//            imgPane.getChildren().add(circle);
//            System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
//        });

    }

    private void drawDotsOnImageView() {

        // Iterate through the buildings and draw dots based on their coordinates
        for (Building building : dijkstraProject.buildings.values()) {
            double x = building.getX();
            double y = building.getY();
            String name = building.getName();

            Circle circle = new Circle(x, y, 4, Color.RED);
            circle.setUserData(name); // the name of the circle is now the name of the building
            circle.setOnMouseClicked(event -> handleCircleClick(building.getName()));

            circle.setOnMouseClicked(event -> {
                String selectedBuilding = (String) circle.getUserData();
                if (comboS.getValue() == null) {
                    // No source building selected yet
                    comboS.setValue(selectedBuilding);
                    circle.setFill(Color.BLUE);
                } else if (comboT.getValue() == null && !selectedBuilding.equals(comboS.getValue())) {
                    // No target building selected and a source building is selected
                    comboT.setValue(selectedBuilding);
                    circle.setFill(Color.GREEN);
                } else if (!selectedBuilding.equals(comboS.getValue()) && !selectedBuilding.equals(comboT.getValue())) {
                    reset();
                }
            });
            imgPane.getChildren().add(circle);
        }
    }

    private void handleCircleClick(String buildingName) {

        // Check if the source building is selected
        if (comboS.getValue() == null) {
            comboS.setValue(buildingName);

        }
        // Check if the target building is selected
        else if (comboT.getValue() == null) {
            comboT.setValue(buildingName);
        }
    }
    // Both source and target buildings are already selected, do nothing

    @FXML
    private void runDijkstra() {
        String sourceBuilding = comboS.getValue();
        String targetBuilding = comboT.getValue();

        if (sourceBuilding != null && targetBuilding != null) {
            // Run Dijkstra's algorithm
            List<String> path = dijkstraProject.findShortestPath(sourceBuilding, targetBuilding);
            int distance = dijkstraProject.getDistance(sourceBuilding, targetBuilding);

            // Display the path and distance
            displayPath(path);
            displayDistance(distance);

            // drawing the lines between buildings
            for (int i = 0; i < path.size() - 1; i++) {
                Building current = dijkstraProject.buildings.get(path.get(i));
                Building next = dijkstraProject.buildings.get(path.get(i + 1));
                double startX = current.getX();
                double startY = current.getY();
                double endX = next.getX();
                double endY = next.getY();
                Line line = new Line(startX, startY, endX, endY);
                imgPane.getChildren().add(line);
            }
        } else {
            // Display an error message if source and/or target buildings are not selected
            pathTA.setText("Please select source and target buildings.");
            distTA.setText("");
        }
    }


    private void displayPath(List<String> path) {
        if (path.isEmpty()) {
            pathTA.setText("No path found.");
        } else {
            String lastBuilding = path.get(path.size() - 1);
            String pathString = String.join(" -> ", path);
            pathString = pathString.replace(" -> " + lastBuilding, "");

            pathTA.setText(pathString);
        }
    }


    private void displayDistance(int distance) {
        if (distance == -1) {
            distTA.setText("Distance not available.");
        } else {
            distTA.setText(String.valueOf(distance));
        }
    }

    @FXML
    private void reset() {
        // Clear combo boxes
        comboS.getSelectionModel().clearSelection();
        comboT.getSelectionModel().clearSelection();

        // Clear text areas
        pathTA.clear();
        distTA.clear();

        // Clear previous lines
        imgPane.getChildren().removeIf(node -> node instanceof Line);

        // Reset colors of circles
        for (Node node : imgPane.getChildren()) {
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                circle.setFill(Color.RED);
            }
        }
    }


}
