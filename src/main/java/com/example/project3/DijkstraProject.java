package com.example.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DijkstraProject {
    Map<String, Building> buildings;

    public DijkstraProject() {
        buildings = new HashMap<>();
    }

    public void readBuildingsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String[] data = line.split(",");
            int numBuildings = Integer.parseInt(data[0].trim());
            int numEdges = Integer.parseInt(data[1].trim());

            for (int i = 0; i < numBuildings; i++) {
                line = reader.readLine();
                String[] buildingData = line.split(",");
                String name = buildingData[0].trim();
                int x = Integer.parseInt(buildingData[1].trim());
                int y = Integer.parseInt(buildingData[2].trim());

                Building building = new Building(name, x, y); //build building object
                buildings.put(name, building);  // add building to the buildings map
            }

            for (int i = 0; i < numEdges; i++) {
                line = reader.readLine();
                String[] edgeData = line.split(",");
                String source = edgeData[0].trim();
                String target = edgeData[1].trim();
                int distance = Integer.parseInt(edgeData[2].trim());

                Building sourceBuilding = buildings.get(source);
                Building targetBuilding = buildings.get(target);

                if (sourceBuilding != null && targetBuilding != null) {
                    //edge connection is created between them using the addEdge method
                    //the connection is created in both directions (source to target and target to source)
                    sourceBuilding.addEdge(targetBuilding, distance);
                    targetBuilding.addEdge(sourceBuilding, distance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getBuildingNames() {
        // For the combo boxes to have the buildings' names
        return new ArrayList<>(buildings.keySet());
    }

    public List<String> findShortestPath(String sourceBuilding, String targetBuilding) {
        Building source = buildings.get(sourceBuilding);
        Building target = buildings.get(targetBuilding);

//        if (source == null || target == null) {
//            return new ArrayList<>(); // Return an empty list if buildings are not found
//        }

        Map<Building, Integer> distances = new HashMap<>();
        //  This map keeps track of the shortest distance from the source building to each building
        Map<Building, Building> previous = new HashMap<>();
        //  Stores the previous building in the shortest path from the source to each building
        PriorityQueue<Building> pQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        //  Essential for choosing the next building to explore based on the shortest distance
        Set<Building> visited = new HashSet<>();

        for (Building building : buildings.values()) {
            distances.put(building, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pQueue.offer(source);

        while (!pQueue.isEmpty()) {
            Building current = pQueue.poll();
            visited.add(current);

            if (current == target) {
                break;
            }

            for (Edge edge : current.getEdges()) {
                Building neighbor = edge.getTarget();
                if (visited.contains(neighbor)) {
                    continue;
                }

                int distanceThroughCurrent = distances.get(current) + edge.getDistance();
                if (distanceThroughCurrent < distances.get(neighbor)) {
                    distances.put(neighbor, distanceThroughCurrent);
                    previous.put(neighbor, current);
                    pQueue.offer(neighbor);
                }
            }
        }

        // Build the path
        List<String> path = new ArrayList<>();
        Building current = target;
        while (current != null) {
            path.add(0, current.getName());
            current = previous.get(current);
        }

        return path;
    }

    public int getDistance(String sourceBuilding, String targetBuilding) {
        Building source = buildings.get(sourceBuilding);
        Building target = buildings.get(targetBuilding);

        if (source == null || target == null) {
            return -1;
        }

        List<String> path = findShortestPath(sourceBuilding, targetBuilding);
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Building current = buildings.get(path.get(i));
            Building next = buildings.get(path.get(i + 1));
            distance += current.getDistanceTo(next);
        }

        return distance;
    }

}

