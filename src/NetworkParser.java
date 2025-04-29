import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Parses network files into FlowNetwork objects.
public class NetworkParser {
    public FlowNetwork parseFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new IOException("Input file is empty: " + filename);
            }
            int numNodes = Integer.parseInt(firstLine.trim());
            if (numNodes < 2) {
                throw new IllegalArgumentException("Network must have at least 2 nodes (source and sink)");
            }

            FlowNetwork network = new FlowNetwork(numNodes);

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    throw new IllegalArgumentException(
                            "Invalid format on line " + lineNumber + ": " + line + ". Expected 'from to capacity'.");
                }

                try {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int capacity = Integer.parseInt(parts[2]);

                    if (from < 0 || from >= numNodes || to < 0 || to >= numNodes) {
                        throw new IllegalArgumentException("Node index out of bounds (0-" + (numNodes - 1)
                                + ") on line " + lineNumber + ": " + line);
                    }
                    if (capacity < 0) {
                        throw new IllegalArgumentException(
                                "Negative capacity not allowed on line " + lineNumber + ": " + line);
                    }

                    network.addEdge(from, to, capacity);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Invalid number format on line " + lineNumber + ": " + line + ". " + e.getMessage());
                }
            }

            return network;
        }
    }
}