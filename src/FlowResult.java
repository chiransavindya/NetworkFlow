import java.util.ArrayList;
import java.util.List;

/**
 * Stores and formats the results of the maximum flow algorithm, including the
 * maximum flow value, the state of edge flows, and the steps taken.
 */
public class FlowResult {
    private int maxFlow;
    private List<Edge> edges;
    private final List<String> steps;

    // Constructor for FlowResult.
    public FlowResult(int initialMaxFlow, List<Edge> initialEdges) {
        this.maxFlow = initialMaxFlow;
        this.edges = initialEdges;
        this.steps = new ArrayList<>();
    }

    // Records an algorithm step.
    public void addStep(String step) {
        steps.add(step);
    }

    // Updates the final maximum flow value.
    public void updateMaxFlow(int finalMaxFlow) {
        this.maxFlow = finalMaxFlow;
    }

    // Updates the edges to their final state after the algorithm.
    public void updateEdges(List<Edge> finalEdges) {
        this.edges = new ArrayList<>(finalEdges);
    }

    // Returns the maximum flow value.
    public int getMaxFlow() {
        return maxFlow;
    }

    /**
     * Prints the final flow through each original edge in a detailed format.
     * e.g. Edge from 0 to 1 || Capacity = 4 || Final Flow = 4
     */
    public void printEdgeFlowsDetailed() {
        for (Edge edge : edges) {
            // Only display original forward edges (capacity > 0)
            if (edge.getCapacity() > 0) {
                System.out.printf("Edge from %d to %d || Capacity = %d || Final Flow = %d\n",
                        edge.getFrom(), edge.getTo(), edge.getCapacity(), edge.getFlow());
            }
        }
    }

    /**
     * Prints the final flow through each edge with positive flow in a simple
     * format.
     * e.g. f(0,1) = 4
     */
    public void printEdgeFlowsSimple() {
        for (Edge edge : edges) {
            if (edge.getFlow() > 0 && edge.getCapacity() > 0) {
                System.out.printf("f(%d,%d) = %d\n", edge.getFrom(), edge.getTo(), edge.getFlow());
            }
        }
    }

    /**
     * Prints all the recorded algorithm steps.
     */
    public void printSteps() {
        for (String step : steps) {
            System.out.println(step);
        }
    }
}