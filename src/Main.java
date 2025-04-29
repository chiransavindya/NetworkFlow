/**
 * Network Flow Algorithm Implementation
 * Student ID: w2081928
 * Name: N.A.C.S.Senarath
 * 
 * This program implements a solution for finding maximum flow in a network
 * using the Ford-Fulkerson algorithm with DFS.
 */

public class Main {
    public static void main(String[] args) {
        // Change this value to test different network files
        String inputFile = "sample_network.txt";
        long startTime = 0, endTime = 0;

        try {
            // --- Print Header ---
            System.out.println("============================================"); 
            System.out.println("Processing File: " + inputFile);
            System.out.println();

            // --- Parse Network ---
            NetworkParser parser = new NetworkParser();
            FlowNetwork network = parser.parseFromFile(inputFile);

            System.out.println("Total Nodes: " + network.getNumNodes()); // Print node count here
            System.out.println();

            // --- Run Algorithm ---
            startTime = System.nanoTime();
            MaxFlowAlgorithm algorithm = new MaxFlowAlgorithm();
            FlowResult result = algorithm.findMaxFlow(network);
            endTime = System.nanoTime();

            // --- Print Algorithm Steps ---
            result.printSteps();
            System.out.println();

            // --- Print Final Flow Details ---
            System.out.println("Final flow through each edge:"); // Header for edge details
            result.printEdgeFlowsDetailed();
            System.out.println();

            // --- Print Summary ---
            System.out.println("Total Maximum Flow: " + result.getMaxFlow());
            double timeTakenMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("Total Time Taken: %.2f ms\n", timeTakenMs);
            System.out.println("============================================"); // Footer Separator

        } catch (Exception e) {
            System.err.println("Error processing file " + inputFile + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}