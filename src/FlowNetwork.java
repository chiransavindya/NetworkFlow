import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flow network using adjacency lists.
 * The network consists of nodes connected by edges with capacities.
 */
public class FlowNetwork {
    private final int numNodes;
    private final List<Edge>[] adjacencyList;
    private final int source;
    private final int sink;

    @SuppressWarnings("unchecked")
    public FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        this.source = 0;
        this.sink = numNodes - 1;
        // Create adjacency list for each node
        adjacencyList = (List<Edge>[]) new List[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    /**
     * Adds an edge and its corresponding residual edge to the network
     */
    public void addEdge(int from, int to, int capacity) {
        Edge edge = new Edge(from, to, capacity);
        Edge residual = new Edge(to, from, 0);

        edge.setResidual(residual);
        residual.setResidual(edge);

        adjacencyList[from].add(edge);
        adjacencyList[to].add(residual);
    }

    /**
     * Returns all edges (including residual) going out from the given node
     */
    public List<Edge> getEdges(int node) {
        return adjacencyList[node];
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int getSource() {
        return source;
    }

    public int getSink() {
        return sink;
    }

    /**
     * Returns all edges in the network for result printing
     */
    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            for (Edge edge : adjacencyList[i]) {
                if (edge.getCapacity() > 0) { // Only original edges, not residual
                    edges.add(edge);
                }
            }
        }
        return edges;
    }
}