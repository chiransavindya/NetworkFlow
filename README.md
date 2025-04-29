# Network Flow Algorithm Implementation
Student ID: w2081928
Name: N.A.C.S.Senarath

This project implements the Ford-Fulkerson algorithm to find the maximum flow in a network.

## Project Structure

```
src/
├── Main.java               # Program entry point
├── FlowNetwork.java        # Network data structure
├── Edge.java              # Edge representation
├── NetworkParser.java     # File parsing
├── MaxFlowAlgorithm.java  # Algorithm implementation
└── FlowResult.java        # Result structure
benchmarks/
├── bridge_*.txt           # Bridge network test cases
└── ladder_*.txt          # Ladder network test cases
sample_network.txt       # Default input file
```

## Compilation

To compile the project, run the following command in the project's root directory:
```bash
javac src/*.java
```
This will create the necessary `.class` files inside the `src` directory.

## Running the Program

The program is currently set up to read its input from a hardcoded file path within `src/Main.java`. By default, this is set to `sample_network.txt`.

To run the program using the default input file:
```bash
java -cp src Main
```

To run the program with a different input file (e.g., one from the `benchmarks` directory):
1.  **Modify `src/Main.java`:** Change the line `String inputFile = "sample_network.txt";` to the desired file path, for example: `String inputFile = "benchmarks/bridge_1.txt";`
2.  **Recompile:** `javac src/Main.java` (or `javac src/*.java` if other files changed).
3.  **Run:** `java -cp src Main`

For larger networks (especially from the benchmarks), you may need to increase the Java heap size using the `-Xmx` flag:
```bash
java -Xmx1g -cp src Main 
```
(Adjust `1g` to `512m`, `2g`, etc., as needed).


## Input File Format

The input file should be formatted as follows:
- First line: number of nodes (n)
- Subsequent lines: `from to capacity`
  - `from`: source node index (0 to n-1)
  - `to`: destination node index (0 to n-1)
  - `capacity`: non-negative integer capacity of the edge

Example (`sample_network.txt`):
```
4
0 1 6
0 2 4
1 2 2
1 3 3
2 3 5
```

## Output Format

The program outputs:
1.  Ford-Fulkerson algorithm steps (each iteration's path, flow added, total flow).
2.  Final flow details for each edge (Source, Destination, Capacity, Final Flow).
3.  Total Maximum Flow value.
4.  Total Time Taken for the algorithm execution (in milliseconds).

## Implementation Details

- Uses the Ford-Fulkerson algorithm with Depth First Search (DFS) for finding augmenting paths.
- Implements a residual graph internally to track available capacity changes.
- Provides detailed step-by-step output of the algorithm's progression.
- Includes basic error handling for file reading and parsing.
- Uses an efficient adjacency list representation for the graph structure.
- Includes execution time measurement.

## Performance

- **Time Complexity:** The theoretical time complexity of the basic Ford-Fulkerson algorithm can be high in the worst case, potentially O(E * f), where E is the number of edges and f is the maximum flow value. Using BFS (Edmonds-Karp) would give O(V * E^2). The current DFS implementation's performance can vary depending on the path chosen.
- **Space Complexity:** O(V + E) due to the adjacency list representation, where V is the number of vertices and E is the number of edges.
- **Memory Usage:** Varies based on input size. Larger benchmark networks might require increasing the Java heap space (e.g., `-Xmx1g` or more) when running. 