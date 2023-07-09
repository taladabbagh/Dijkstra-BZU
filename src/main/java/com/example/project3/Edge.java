package com.example.project3;

class Edge {
    private Building target;
    private int distance;

    public Edge(Building target, int distance) {
        this.target = target;
        this.distance = distance;
    }

    public Building getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }
}
