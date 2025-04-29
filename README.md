# Network Flow Algorithm

<div align="center">
  <h3>Maximum Flow Problem Solver</h3>
  <p><i>A Java implementation of the Ford-Fulkerson algorithm</i></p>
</div>

---

## 📋 Overview

This application solves the maximum flow problem using the Ford-Fulkerson algorithm with Depth-First Search. It calculates the maximum possible flow from a source node (node 0) to a sink node (last node) in a directed graph where each edge has a capacity constraint.

<div align="center">
  <i>Example network with max flow of 8:</i><br>
  <pre>
    [0] --6--> [1] --3--> [3]
     |          |          ⬆
     |          |          |
     4          2          5
     |          |          |
     ↓          ↓          |
    [2] --------→----------
  </pre>
</div>

## 🗂️ Project Structure

```
src/
├── Main.java               # Program entry point
├── FlowNetwork.java        # Network data structure
├── Edge.java               # Edge representation
├── NetworkParser.java      # File parsing
├── MaxFlowAlgorithm.java   # Algorithm implementation
└── FlowResult.java         # Result structure
sample_network.txt          # Default test network
```

## 🚀 Getting Started

### Compilation

```bash
javac src/*.java
```

### Execution

```bash
java -cp src Main
```

The program reads from `sample_network.txt` by default and displays:
- Total nodes in the network
- Each augmenting path found during execution
- Final flow values for each edge
- Total maximum flow and execution time

## 📄 Input File Format

The input file `sample_network.txt` follows this format:

```
4
0 1 6
0 2 4
1 2 2
1 3 3
2 3 5
```

Where:
- First line: number of nodes (n)
- Each subsequent line: `source destination capacity`
- Node 0 is always the source
- Node (n-1) is always the sink (node 3 in this example)

## 📊 Output Example

```
============================================
Processing File: sample_network.txt

Total Nodes: 4

Augmenting Path 1: 0 -> 1 -> 3 | Bottleneck = 3
Augmenting Path 2: 0 -> 2 -> 3 | Bottleneck = 4
Augmenting Path 3: 0 -> 1 -> 2 -> 3 | Bottleneck = 1
No more augmenting paths.

Final flow through each edge:
Edge from 0 to 1 | Capacity = 6 | Final Flow = 4
Edge from 0 to 2 | Capacity = 4 | Final Flow = 4
Edge from 1 to 2 | Capacity = 2 | Final Flow = 1
Edge from 1 to 3 | Capacity = 3 | Final Flow = 3
Edge from 2 to 3 | Capacity = 5 | Final Flow = 5

Total Maximum Flow: 8
Total Time Taken: 5.23 ms
============================================
```

## 🔧 How It Works

1. **Network Representation**: The graph is represented using an adjacency list with each edge storing its capacity and current flow.

2. **Ford-Fulkerson Algorithm**:
   - Start with zero flow on all edges
   - Repeatedly find augmenting paths from source to sink
   - For each path, find the bottleneck (minimum residual capacity)
   - Augment flow along the path by this bottleneck amount
   - Continue until no more augmenting paths can be found

3. **Path Finding**: Uses depth-first search (DFS) to find augmenting paths through the residual graph.

## 🧠 Implementation Details

- **Data Structure**: Efficient adjacency list representation
- **Algorithm**: Ford-Fulkerson with DFS for finding augmenting paths
- **Time Complexity**: O(E·f) where E is the number of edges and f is the maximum flow
- **Space Complexity**: O(V+E) where V is the number of vertices
- **Residual Graph**: Maintained during algorithm execution to track available capacity

## 📝 Testing with Your Own Network

To test with a different network:

1. Create a new text file following the input format described above
2. Modify `src/Main.java`: Change the line `String inputFile = "sample_network.txt";` to your file path
3. Recompile: `javac src/Main.java`
4. Run: `java -cp src Main`

---

<div align="center">
  <p><i>This implementation serves as an educational tool for understanding network flow algorithms</i></p>
</div> 