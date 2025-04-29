import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Ford-Fulkerson maximum flow algorithm using Depth First Search
 * (DFS)
 * to find augmenting paths in the residual graph.
 */
public class MaxFlowAlgorithm {
    private boolean[] visited;
    private List<Edge> path; // Stores the edges of the current augmenting path found by DFS
    private FlowNetwork network;
    private FlowResult result;
    private int iteration = 0;

    // Finds the maximum flow using the Ford-Fulkerson algorithm.
    public FlowResult findMaxFlow(FlowNetwork network) {
        this.network = network;
        int maxFlow = 0;
        iteration = 0;

        List<Edge> initialEdges = new ArrayList<>(network.getAllEdges());
        this.result = new FlowResult(maxFlow, initialEdges);

        while (true) {
            visited = new boolean[network.getNumNodes()];
            path = new ArrayList<>();

            if (!findPath(network.getSource(), network.getSink())) {
                break;
            }

            int bottleneck = calculateBottleneck();

            augmentFlow(bottleneck);

            maxFlow += bottleneck;
            iteration++;

            List<Integer> completePath = buildCompletePath();

            StringBuilder pathStr = new StringBuilder();
            for (int i = 0; i < completePath.size(); i++) {
                pathStr.append(completePath.get(i));
                if (i < completePath.size() - 1) {
                    pathStr.append("->");
                }
            }

            String stepDesc = String.format("Iteration %d: Path: %s - Flow added: %d - Total flow now: %d",
                    iteration, pathStr.toString(), bottleneck, maxFlow);

            this.result.addStep(stepDesc);
        }

        this.result.updateMaxFlow(maxFlow);
        this.result.updateEdges(network.getAllEdges());

        return this.result;
    }

    // Builds the complete augmenting path as a list of nodes.
    private List<Integer> buildCompletePath() {
        List<Integer> completePath = new ArrayList<>();
        completePath.add(network.getSource());
        for (Edge edge : path) {
            completePath.add(edge.getTo());
        }
        return completePath;
    }

    // Finds an augmenting path from node to sink using DFS.
    private boolean findPath(int node, int sink) {
        if (node == sink)
            return true;

        visited[node] = true;

        for (Edge edge : network.getEdges(node)) {
            // Explore edges with positive residual capacity to unvisited nodes
            if (!visited[edge.getTo()] && edge.getResidualCapacity() > 0) {
                path.add(edge); // Add edge to the current path
                if (findPath(edge.getTo(), sink)) {
                    return true;
                }
                path.remove(path.size() - 1); // Backtrack: remove edge if path not found from here
            }
        }

        return false;
    }

    // Calculates the bottleneck capacity (minimum residual capacity) of the current path.
    private int calculateBottleneck() {
        int bottleneck = Integer.MAX_VALUE;
        for (Edge edge : path) {
            bottleneck = Math.min(bottleneck, edge.getResidualCapacity());
        }
        // If path is somehow empty, bottleneck remains MAX_VALUE, which should not
        // happen if findPath returned true.
        return (bottleneck == Integer.MAX_VALUE) ? 0 : bottleneck;
    }

    // Augments flow along the current path by the bottleneck amount.
    private void augmentFlow(int amount) {
        for (Edge edge : path) {
            edge.addFlow(amount); // addFlow automatically updates the residual edge
        }
    }
}