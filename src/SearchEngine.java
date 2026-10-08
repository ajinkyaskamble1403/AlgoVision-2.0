public class SearchEngine {

    // ==============================
    // BST NODE
    // ==============================

    private class BSTNode {

        String key;
        String[] record;
        BSTNode left;
        BSTNode right;

        BSTNode(String key, String[] record) {
            this.key = key;
            this.record = record;
        }
    }

    private BSTNode root;

    // ==============================
    // HEAP DATA
    // ==============================

    private String[][] heap;
    private int heapSize;
    private int heapCapacity;

    public SearchEngine(int heapCapacity) {

        root = null;

        this.heapCapacity = heapCapacity;
        heap = new String[heapCapacity][];
        heapSize = 0;
    }

    // ==============================
    // BST INSERT
    // ==============================

    public void insertIntoBST(String[] record) {

        if (record == null || record.length == 0) {
            return;
        }

        String key = record[0];

        root = insertBST(root, key, record);
    }

    private BSTNode insertBST(BSTNode node, String key, String[] record) {

        if (node == null) {
            return new BSTNode(key, record);
        }

        int comparison = key.compareToIgnoreCase(node.key);

        if (comparison < 0) {

            node.left = insertBST(node.left, key, record);

        } else if (comparison > 0) {

            node.right = insertBST(node.right, key, record);

        } else {

            node.record = record;
        }

        return node;
    }

    // ==============================
    // BST SEARCH
    // ==============================

    public String[] searchBST(String key) {

        BSTNode current = root;

        while (current != null) {

            int comparison = key.compareToIgnoreCase(current.key);

            if (comparison == 0) {
                return current.record;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // ==============================
    // BST IN-ORDER DISPLAY
    // ==============================

    public void displayBST() {

        if (root == null) {
            System.out.println("\nBST is empty.");
            return;
        }

        System.out.println("\n========== BST SEARCH INDEX ==========");

        inOrder(root);
    }

    private void inOrder(BSTNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);

        System.out.println(node.key);

        inOrder(node.right);
    }

    // ==============================
    // HEAP INSERT
    // ==============================

    public boolean insertIntoHeap(String[] record) {

        if (record == null || record.length < 6) {
            return false;
        }

        if (heapSize >= heapCapacity) {
            System.out.println("Heap is full.");
            return false;
        }

        heap[heapSize] = record;

        int current = heapSize;

        heapSize++;

        while (current > 0) {

            int parent = (current - 1) / 2;

            if (getPriority(heap[parent]) <= getPriority(heap[current])) {
                break;
            }

            swapHeapElements(parent, current);

            current = parent;
        }

        return true;
    }

    // ==============================
    // HEAP REMOVE
    // ==============================

    public String[] extractHighestPriority() {

        if (heapSize == 0) {
            return null;
        }

        String[] highestPriority = heap[0];

        heap[0] = heap[heapSize - 1];

        heap[heapSize - 1] = null;

        heapSize--;

        heapifyDown(0);

        return highestPriority;
    }

    private void heapifyDown(int index) {

        while (true) {

            int left = (2 * index) + 1;
            int right = (2 * index) + 2;

            int smallest = index;

            if (left < heapSize &&
                    getPriority(heap[left]) < getPriority(heap[smallest])) {

                smallest = left;
            }

            if (right < heapSize &&
                    getPriority(heap[right]) < getPriority(heap[smallest])) {

                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swapHeapElements(index, smallest);

            index = smallest;
        }
    }

    private void swapHeapElements(int first, int second) {

        String[] temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    // Priority is stored in column 6 of logistics.csv
    private int getPriority(String[] record) {

        try {
            return Integer.parseInt(record[5].trim());

        } catch (NumberFormatException e) {

            return Integer.MAX_VALUE;
        }
    }

    // ==============================
    // HEAP DISPLAY
    // ==============================

    public void displayHeap() {

        if (heapSize == 0) {
            System.out.println("\nPriority heap is empty.");
            return;
        }

        System.out.println("\n========== PRIORITY HEAP ==========");

        for (int i = 0; i < heapSize; i++) {

            System.out.println(
                    "Shipment: " + heap[i][0]
                    + " | Priority: " + getPriority(heap[i])
            );
        }
    }

    // ==============================
    // UTILITY METHODS
    // ==============================

    public int getHeapSize() {
        return heapSize;
    }

    public boolean isBSTEmpty() {
        return root == null;
    }

    public boolean isHeapEmpty() {
        return heapSize == 0;
    }
}