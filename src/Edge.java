/**
 * Represents an edge in the flow network.
 * Each edge maintains its capacity, current flow, and a reference to its
 * residual edge.
 */
public class Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow;
    private Edge residual;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public Edge getResidual() {
        return residual;
    }

    /**
     * Returns the remaining capacity that can flow through this edge
     */
    public int getResidualCapacity() {
        return capacity - flow;
    }

    /**
     * Adds the specified amount of flow to this edge and updates the residual edge
     */
    public void addFlow(int amount) {
        flow += amount;
        residual.flow -= amount;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d): %d/%d", from, to, flow, capacity);
    }
}