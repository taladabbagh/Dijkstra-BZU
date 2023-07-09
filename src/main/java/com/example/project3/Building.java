package com.example.project3;

import java.util.ArrayList;
import java.util.List;

class Building {
    private String name;
    private int x;
    private int y;
    private List<Edge> edges;

    public Building(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEdge(com.example.project3.Building target, int distance) {
        edges.add(new Edge(target, distance));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getDistanceTo(com.example.project3.Building target) {
        for (Edge edge : edges) {
            if (edge.getTarget() == target) {
                return edge.getDistance();
            }
        }
        return -1;
    }
}
