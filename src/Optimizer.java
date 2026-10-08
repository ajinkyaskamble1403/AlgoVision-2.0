public class Optimizer {

    // =========================================================
    // GREEDY ALGORITHM
    // =========================================================

    /*
     * Selects shipments based on highest priority while staying
     * within the available vehicle weight capacity.
     *
     * Priority:
     * 1 = Highest
     * 2 = Medium
     * 3 = Lowest
     */
    public String[][] greedyShipmentOptimization(
            String[][] shipments,
            int capacity) {

        if (shipments == null || shipments.length == 0) {
            return new String[0][];
        }

        // Copy the input so the original catalog is not modified
        String[][] sortedShipments = new String[shipments.length][];

        for (int i = 0; i < shipments.length; i++) {
            sortedShipments[i] = shipments[i];
        }

        // Sort according to priority
        for (int i = 0; i < sortedShipments.length - 1; i++) {

            for (int j = 0; j < sortedShipments.length - i - 1; j++) {

                int priority1 = getPriority(sortedShipments[j]);
                int priority2 = getPriority(sortedShipments[j + 1]);

                if (priority1 > priority2) {

                    String[] temp = sortedShipments[j];
                    sortedShipments[j] = sortedShipments[j + 1];
                    sortedShipments[j + 1] = temp;
                }
            }
        }

        // Store selected shipments
        String[][] selected = new String[shipments.length][];
        int selectedCount = 0;
        int currentWeight = 0;

        for (String[] shipment : sortedShipments) {

            int weight = getWeight(shipment);

            if (currentWeight + weight <= capacity) {

                selected[selectedCount] = shipment;
                selectedCount++;

                currentWeight += weight;
            }
        }

        return resizeArray(selected, selectedCount);
    }

    // =========================================================
    // DYNAMIC PROGRAMMING
    // =========================================================

    /*
     * 0/1 Knapsack approach.
     *
     * Each shipment has:
     * Weight  -> resource consumption
     * Priority -> value
     *
     * The algorithm maximizes total priority value
     * without exceeding vehicle capacity.
     */
    public String[][] dynamicProgrammingOptimization(
            String[][] shipments,
            int capacity) {

        if (shipments == null || shipments.length == 0 || capacity <= 0) {
            return new String[0][];
        }

        int n = shipments.length;

        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {

            int weight = getWeight(shipments[i - 1]);
            int value = getPriorityValue(shipments[i - 1]);

            for (int w = 0; w <= capacity; w++) {

                if (weight <= w) {

                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            value + dp[i - 1][w - weight]
                    );

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Determine selected shipments
        String[][] selected = new String[n][];
        int selectedCount = 0;

        int remainingCapacity = capacity;

        for (int i = n; i > 0; i--) {

            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {

                selected[selectedCount] = shipments[i - 1];
                selectedCount++;

                remainingCapacity -= getWeight(shipments[i - 1]);
            }
        }

        return resizeArray(selected, selectedCount);
    }

    // =========================================================
    // RESULT DISPLAY
    // =========================================================

    public void displayOptimizationResult(
            String algorithm,
            String[][] result) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("          " + algorithm);
        System.out.println("==================================================");

        if (result.length == 0) {

            System.out.println("No shipments selected.");
            return;
        }

        int totalWeight = 0;
        int totalPriority = 0;

        for (String[] shipment : result) {

            displayShipment(shipment);

            totalWeight += getWeight(shipment);
            totalPriority += getPriorityValue(shipment);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Shipments selected : " + result.length);
        System.out.println("Total weight       : " + totalWeight);
        System.out.println("Total priority     : " + totalPriority);
        System.out.println("==================================================");
    }

    // =========================================================
    // COMPARISON
    // =========================================================

    public void compareAlgorithms(
            String[][] shipments,
            int capacity) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("          OPTIMIZATION COMPARISON");
        System.out.println("==================================================");

        long greedyStart = System.nanoTime();

        String[][] greedyResult =
                greedyShipmentOptimization(shipments, capacity);

        long greedyEnd = System.nanoTime();

        long dpStart = System.nanoTime();

        String[][] dpResult =
                dynamicProgrammingOptimization(shipments, capacity);

        long dpEnd = System.nanoTime();

        displayOptimizationResult(
                "GREEDY RESULT",
                greedyResult
        );

        System.out.println(
                "Execution time: "
                + (greedyEnd - greedyStart)
                + " ns"
        );

        displayOptimizationResult(
                "DYNAMIC PROGRAMMING RESULT",
                dpResult
        );

        System.out.println(
                "Execution time: "
                + (dpEnd - dpStart)
                + " ns"
        );

        System.out.println();
        System.out.println("==================================================");
        System.out.println("             ALGORITHM COMPARISON");
        System.out.println("==================================================");
        System.out.println(
                "Greedy selected shipments : "
                + greedyResult.length
        );
        System.out.println(
                "DP selected shipments     : "
                + dpResult.length
        );
        System.out.println("==================================================");
    }

    // =========================================================
    // HELPER METHODS
    // =========================================================

    private int getWeight(String[] shipment) {

        try {
            return Integer.parseInt(shipment[4].trim());

        } catch (Exception e) {

            return 0;
        }
    }

    private int getPriority(String[] shipment) {

        try {
            return Integer.parseInt(shipment[5].trim());

        } catch (Exception e) {

            return Integer.MAX_VALUE;
        }
    }

    /*
     * Higher priority shipments receive higher value.
     *
     * Priority 1 -> value 3
     * Priority 2 -> value 2
     * Priority 3 -> value 1
     */
    private int getPriorityValue(String[] shipment) {

        int priority = getPriority(shipment);

        if (priority == 1) {
            return 3;
        }

        if (priority == 2) {
            return 2;
        }

        if (priority == 3) {
            return 1;
        }

        return 0;
    }

    private void displayShipment(String[] shipment) {

        for (int i = 0; i < shipment.length; i++) {

            System.out.print(shipment[i]);

            if (i < shipment.length - 1) {
                System.out.print(" | ");
            }
        }

        System.out.println();
    }

    private String[][] resizeArray(
            String[][] array,
            int size) {

        String[][] resized = new String[size][];

        for (int i = 0; i < size; i++) {
            resized[i] = array[i];
        }

        return resized;
    }
}
