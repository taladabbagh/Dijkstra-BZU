# Dijkstra's Algorithm Implementation

This repository contains an implementation of **Dijkstra's Algorithm**, a fundamental graph traversal algorithm used to find the shortest path between nodes in a graph. This project was developed as part of my coursework at Birzeit University (BZU) and showcases my understanding of algorithms, data structures, and problem-solving skills.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Challenges and Solutions](#challenges-and-solutions)
4. [Installation](#installation)
5. [Usage](#usage)
6. [License](#license)

---

## Project Overview

Dijkstra's Algorithm is widely used in applications like network routing, GPS navigation, and more. This implementation demonstrates how the algorithm works step-by-step and includes:

- A **graph representation** using adjacency lists.
- A **priority queue** to efficiently select the next node with the smallest distance.
- Visualization of the shortest path from a source node to all other nodes.

This project was written in **Java** and is designed to be easy to understand and extend.

---

## Features

- **Graph Input**: Accepts a graph as input, either manually or from a file.
- **Shortest Path Calculation**: Computes the shortest path from a source node to all other nodes.
- **Step-by-Step Output**: Prints the algorithm's progress at each step for better understanding.
- **Customizable**: Easily extendable to handle weighted graphs or additional constraints.

---

## Challenges and Solutions

### Challenge 1: Calculating Distances Using Coordinates from a Picture
- **Problem**:  
  In this project, I needed to calculate the shortest path between buildings on a university campus. However, I didn’t have access to the exact distances between the buildings. Instead, I had a map image with the buildings marked, and I needed to derive the distances from the coordinates of the buildings on the map.

- **Solution**:  
  To address this challenge, I took the following steps:
  1. **Extracted Coordinates**: I manually identified the coordinates (x, y) of each building from the map image.
  2. **Calculated Euclidean Distances**: Using the coordinates, I calculated the Euclidean distance between each pair of buildings using the formula:
     ```
     distance = sqrt((x2 - x1)^2 + (y2 - y1)^2)
     ```
  3. **Created the Graph**: I used these calculated distances to build a weighted graph, where each building was a node, and the edges represented the distances between them.
  4. **Validated the Distances**: I cross-checked the calculated distances with known distances (e.g., walking paths) to ensure accuracy.

This approach allowed me to create a realistic graph for Dijkstra's Algorithm, even without exact distance data.

---

### Challenge 2: Handling Disconnected Graphs and Unreachable Nodes
- **Problem**:  
  During testing, I encountered an issue where the algorithm would fail or produce incorrect results when the graph was disconnected (i.e., some nodes were unreachable from the source node). For example, if the source node was in one isolated subgraph and the target node was in another, the algorithm would not handle this gracefully.

- **Solution**:  
  To address this issue, I made the following improvements to the algorithm:
  1. **Initialized Distances to Infinity**: I initialized the distance to all nodes as `Infinity` (or a very large number) to represent unreachable nodes.
  2. **Checked for Unreachable Nodes**: After running the algorithm, I added a check to identify nodes that remained at `Infinity` distance, indicating they were unreachable from the source.
  3. **Provided Clear Output**: For unreachable nodes, the program now outputs a clear message (e.g., "Node X is unreachable from the source") instead of returning incorrect or misleading results.
  4. **Tested Edge Cases**: I tested the algorithm with various disconnected graphs to ensure it handled all cases correctly.

This improvement made the algorithm more robust and user-friendly, ensuring it could handle real-world scenarios where not all nodes are connected.

---

## Installation

To run this project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/taladabbagh/Dijkstra-BZU.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd Dijkstra-BZU
   ```

3. **Compile and Run the Program**:
   - Compile the main Java file:
     ```bash
     javac Main.java
     ```
   - Run the program:
     ```bash
     java Main
     ```

---

## Usage

1. **Input the Graph**:
   - The program accepts a graph as input. You can either:
     - Enter the graph manually when prompted (e.g., adjacency list or matrix).
     - Provide a file containing the graph data.

2. **Run the Algorithm**:
   - The program will compute the shortest path from the source node to all other nodes.

3. **View the Output**:
   - The program will display the shortest distances and the paths step-by-step.
   - If a node is unreachable, the program will explicitly state this.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
