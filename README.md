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

This project was written in Java and is designed to be easy to understand and extend.

---

## Features

- **Graph Input**: Accepts a graph as input, either manually or from a file.
- **Shortest Path Calculation**: Computes the shortest path from a source node to all other nodes.
- **Step-by-Step Output**: Prints the algorithm's progress at each step for better understanding.
- **Customizable**: Easily extendable to handle weighted graphs or additional constraints.

---

## Challenges and Solutions

### Challenge: Efficiently Implementing the Priority Queue
- **Problem**:  
  Initially, I used a simple list to store and retrieve nodes, which resulted in inefficient performance, especially for large graphs. The time complexity for finding the node with the smallest distance was O(n), making the overall algorithm slow.

- **Solution**:  
  To optimize the algorithm, I implemented a **min-heap** (priority queue) to store nodes. This reduced the time complexity of retrieving the node with the smallest distance to O(log n). Here’s how I addressed the challenge:
  1. **Researched Heap Data Structures**: I studied how min-heaps work and their advantages over lists.
  2. **Implemented the Min-Heap**: I wrote a custom min-heap class to manage the nodes efficiently.
  3. **Integrated the Heap into Dijkstra's Algorithm**: I replaced the list with the min-heap, ensuring the algorithm remained correct while improving performance.

This optimization made the algorithm scalable and suitable for larger graphs.

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

3. **Run the program**:
   - If written in Python:
     ```bash
     python dijkstra.py
     ```
   - If written in C++:
     ```bash
     g++ dijkstra.cpp -o dijkstra
     ./dijkstra
     ```

---

## Usage

1. **Input the Graph**:
   - The program accepts a graph as input. You can either:
     - Enter the graph manually when prompted.
     - Provide a file containing the graph data (e.g., adjacency list or matrix).

2. **Run the Algorithm**:
   - The program will compute the shortest path from the source node to all other nodes.

3. **View the Output**:
   - The program will display the shortest distances and the paths step-by-step.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
