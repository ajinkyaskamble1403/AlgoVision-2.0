import java.util.Scanner;

public class CLI {

    private Scanner scanner;
    private DataIngestion dataIngestion;
    private Catalog catalog;
    private SearchEngine searchEngine;
    private Optimizer optimizer;
    private GraphEngine graphEngine;
    private SectorManager sectorManager;

    public CLI() {

        scanner = new Scanner(System.in);

        dataIngestion = new DataIngestion();

        catalog = new Catalog(100);

        searchEngine = new SearchEngine(100);

        optimizer = new Optimizer();

        sectorManager = new SectorManager();

        createLogisticsGraph();
    }

    // =========================================================
    // START APPLICATION
    // =========================================================

    public void start() {

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice =
                    Utils.getInteger(
                            scanner,
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    logisticsMenu();
                    break;

                case 2:
                    sectorManager.showEcommerceAnalytics();
                    Utils.pause(scanner);
                    break;

                case 3:
                    sectorManager.showFintechAnalytics();
                    Utils.pause(scanner);
                    break;

                case 4:
                    showAlgorithmOverview();
                    break;

                case 5:
                    showComplexityReport();
                    break;

                case 6:

                    running = false;

                    System.out.println();
                    System.out.println(
                            "Thank you for using AlgoVision."
                    );

                    break;

                default:

                    System.out.println(
                            "\nInvalid choice."
                    );

                    Utils.pause(scanner);
            }
        }

        scanner.close();
    }

    // =========================================================
    // MAIN MENU
    // =========================================================

    private void displayMainMenu() {

        Utils.printHeader(
                "ALGOVISION 2.0"
        );

        System.out.println(
                "1. Logistics"
        );

        System.out.println(
                "2. E-Commerce"
        );

        System.out.println(
                "3. Fintech"
        );

        System.out.println(
                "4. Algorithm Overview"
        );

        System.out.println(
                "5. Complexity Report"
        );

        System.out.println(
                "6. Exit"
        );

        Utils.printSeparator();
    }

    // =========================================================
    // LOGISTICS MENU
    // =========================================================

    private void logisticsMenu() {

        boolean back = false;

        while (!back) {

            Utils.printHeader(
                    "LOGISTICS MODULE"
            );

            System.out.println("1. Load Shipment Data");
            System.out.println("2. View Ingestion Queue");
            System.out.println("3. Process Next Shipment");
            System.out.println("4. Search Shipment");
            System.out.println("5. View Ingestion Statistics");

            System.out.println("6. View Shipment Catalog");
            System.out.println("7. Remove Shipment from Catalog");
            System.out.println("8. Search Shipment in Catalog");

            System.out.println("9. Build Search Index");
            System.out.println("10. Search Using BST");
            System.out.println("11. View BST Index");

            System.out.println("12. Add Shipment to Priority Heap");
            System.out.println("13. View Priority Heap");
            System.out.println("14. Process Highest Priority Shipment");

            System.out.println("15. Optimize Shipment Loading");

            System.out.println("16. View Logistics Network");
            System.out.println("17. BFS Network Traversal");
            System.out.println("18. DFS Network Traversal");
            System.out.println("19. Find Shortest Route");

            System.out.println("20. Back to Main Menu");

            Utils.printSeparator();

            int choice =
                    Utils.getInteger(
                            scanner,
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    loadLogisticsData();
                    break;

                case 2:
                    dataIngestion.displayQueue();
                    Utils.pause(scanner);
                    break;

                case 3:
                    processShipment();
                    break;

                case 4:
                    searchShipment();
                    break;

                case 5:
                    dataIngestion.displayStatistics();
                    Utils.pause(scanner);
                    break;

                case 6:
                    catalog.displayCatalog();
                    Utils.pause(scanner);
                    break;

                case 7:
                    removeShipment();
                    break;

                case 8:
                    searchCatalog();
                    break;

                case 9:
                    buildSearchIndex();
                    break;

                case 10:
                    searchUsingBST();
                    break;

                case 11:
                    searchEngine.displayBST();
                    Utils.pause(scanner);
                    break;

                case 12:
                    addShipmentToHeap();
                    break;

                case 13:
                    searchEngine.displayHeap();
                    Utils.pause(scanner);
                    break;

                case 14:
                    processHighestPriorityShipment();
                    break;

                case 15:
                    optimizeShipments();
                    break;

                case 16:
                    graphEngine.displayGraph();
                    Utils.pause(scanner);
                    break;

                case 17:
                    bfsNetwork();
                    break;

                case 18:
                    dfsNetwork();
                    break;

                case 19:
                    shortestRoute();
                    break;

                case 20:
                    back = true;
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice."
                    );

                    Utils.pause(scanner);
            }
        }
    }

    // =========================================================
    // DATA INGESTION
    // =========================================================

    private void loadLogisticsData() {

        System.out.println(
                "\nLoading logistics shipment data..."
        );

        int recordsLoaded =
                dataIngestion.loadCSV(
                        "data/logistics.csv"
                );

        if (recordsLoaded > 0) {

            System.out.println(
                    recordsLoaded
                    + " shipment records loaded successfully."
            );
        }

        Utils.pause(scanner);
    }

    private void processShipment() {

        String[] record =
                dataIngestion.processNextRecord();

        if (record == null) {

            System.out.println(
                    "\nNo shipments are currently waiting."
            );

        } else {

            Utils.printHeader(
                    "PROCESSED SHIPMENT"
            );

            dataIngestion.displayRecord(record);

            if (catalog.addRecord(record)) {

                System.out.println(
                        "Shipment added to catalog successfully."
                );
            }
        }

        Utils.pause(scanner);
    }

    private void searchShipment() {

        String id =
                Utils.getString(
                        scanner,
                        "\nEnter Shipment ID: "
                );

        dataIngestion.displaySearchResult(id);

        Utils.pause(scanner);
    }

    // =========================================================
    // CATALOG
    // =========================================================

    private void removeShipment() {

        String id =
                Utils.getString(
                        scanner,
                        "\nEnter Shipment ID to remove: "
                );

        boolean removed =
                catalog.removeRecord(id);

        if (removed) {

            System.out.println(
                    "\nShipment "
                    + id
                    + " removed successfully."
            );

        } else {

            System.out.println(
                    "\nShipment "
                    + id
                    + " was not found."
            );
        }

        Utils.pause(scanner);
    }

    private void searchCatalog() {

        String id =
                Utils.getString(
                        scanner,
                        "\nEnter Shipment ID to search: "
                );

        String[] record =
                catalog.searchRecord(id);

        if (record == null) {

            System.out.println(
                    "\nShipment "
                    + id
                    + " was not found."
            );

        } else {

            Utils.printHeader(
                    "CATALOG SEARCH RESULT"
            );

            dataIngestion.displayRecord(record);
        }

        Utils.pause(scanner);
    }

    // =========================================================
    // BST
    // =========================================================

    private void buildSearchIndex() {

        System.out.println(
                "\nBuilding BST search index..."
        );

        int recordsAdded = 0;

        for (int i = 1; i <= 10; i++) {

            String id =
                    String.format(
                            "S%03d",
                            i
                    );

            String[] record =
                    catalog.searchRecord(id);

            if (record != null) {

                searchEngine.insertIntoBST(record);

                recordsAdded++;
            }
        }

        if (recordsAdded == 0) {

            System.out.println(
                    "\nNo catalog records available."
            );

            System.out.println(
                    "Process shipments first."
            );

        } else {

            System.out.println(
                    "\nBST search index built successfully."
            );

            System.out.println(
                    "Records indexed: "
                    + recordsAdded
            );
        }

        Utils.pause(scanner);
    }

    private void searchUsingBST() {

        String id =
                Utils.getString(
                        scanner,
                        "\nEnter Shipment ID: "
                );

        String[] record =
                searchEngine.searchBST(id);

        if (record == null) {

            System.out.println(
                    "\nShipment "
                    + id
                    + " was not found."
            );

        } else {

            Utils.printHeader(
                    "BST SEARCH RESULT"
            );

            dataIngestion.displayRecord(record);
        }

        Utils.pause(scanner);
    }

    // =========================================================
    // HEAP
    // =========================================================

    private void addShipmentToHeap() {

        String id =
                Utils.getString(
                        scanner,
                        "\nEnter Shipment ID: "
                );

        String[] record =
                catalog.searchRecord(id);

        if (record == null) {

            System.out.println(
                    "\nShipment "
                    + id
                    + " was not found."
            );

        } else if (
                searchEngine.insertIntoHeap(record)
        ) {

            System.out.println(
                    "\nShipment "
                    + id
                    + " added to priority heap."
            );

        } else {

            System.out.println(
                    "\nUnable to add shipment."
            );
        }

        Utils.pause(scanner);
    }

    private void processHighestPriorityShipment() {

        String[] record =
                searchEngine.extractHighestPriority();

        if (record == null) {

            System.out.println(
                    "\nPriority heap is empty."
            );

        } else {

            Utils.printHeader(
                    "HIGHEST PRIORITY SHIPMENT"
            );

            dataIngestion.displayRecord(record);

            System.out.println(
                    "Shipment processed successfully."
            );
        }

        Utils.pause(scanner);
    }

    // =========================================================
    // OPTIMIZER
    // =========================================================

    private void optimizeShipments() {

        if (catalog.getSize() == 0) {

            System.out.println(
                    "\nCatalog is empty."
            );

            System.out.println(
                    "Process shipments into the catalog first."
            );

            Utils.pause(scanner);

            return;
        }

        Utils.printHeader(
                "SHIPMENT OPTIMIZATION"
        );

        int capacity =
                Utils.getInteger(
                        scanner,
                        "Enter vehicle capacity (kg): "
                );

        if (capacity <= 0) {

            System.out.println(
                    "\nCapacity must be greater than zero."
            );

            Utils.pause(scanner);

            return;
        }

        String[][] shipments =
                getCatalogRecords();

        optimizer.compareAlgorithms(
                shipments,
                capacity
        );

        Utils.pause(scanner);
    }

    private String[][] getCatalogRecords() {

        int size =
                catalog.getSize();

        String[][] records =
                new String[size][];

        int index = 0;

        for (int i = 1; i <= 10; i++) {

            String id =
                    String.format(
                            "S%03d",
                            i
                    );

            String[] record =
                    catalog.searchRecord(id);

            if (record != null &&
                    index < records.length) {

                records[index] = record;

                index++;
            }
        }

        return records;
    }

    // =========================================================
    // GRAPH
    // =========================================================

    private void createLogisticsGraph() {

        String[] cities = {
                "Mumbai",
                "Pune",
                "Nashik",
                "Surat",
                "Ahmedabad",
                "Nagpur"
        };

        graphEngine =
                new GraphEngine(cities);

        graphEngine.addEdge(
                "Mumbai",
                "Pune",
                150
        );

        graphEngine.addEdge(
                "Mumbai",
                "Nashik",
                170
        );

        graphEngine.addEdge(
                "Mumbai",
                "Surat",
                280
        );

        graphEngine.addEdge(
                "Mumbai",
                "Ahmedabad",
                530
        );

        graphEngine.addEdge(
                "Pune",
                "Nagpur",
                720
        );

        graphEngine.addEdge(
                "Nashik",
                "Surat",
                290
        );

        graphEngine.addEdge(
                "Surat",
                "Ahmedabad",
                270
        );
    }

    private void bfsNetwork() {

        System.out.println(
                "\nAvailable cities:"
        );

        System.out.println(
                "Mumbai, Pune, Nashik, Surat, Ahmedabad, Nagpur"
        );

        String city =
                Utils.getString(
                        scanner,
                        "\nEnter starting city: "
                );

        graphEngine.bfs(city);

        Utils.pause(scanner);
    }

    private void dfsNetwork() {

        System.out.println(
                "\nAvailable cities:"
        );

        System.out.println(
                "Mumbai, Pune, Nashik, Surat, Ahmedabad, Nagpur"
        );

        String city =
                Utils.getString(
                        scanner,
                        "\nEnter starting city: "
                );

        graphEngine.dfs(city);

        Utils.pause(scanner);
    }

    private void shortestRoute() {

        System.out.println(
                "\nAvailable cities:"
        );

        System.out.println(
                "Mumbai, Pune, Nashik, Surat, Ahmedabad, Nagpur"
        );

        String source =
                Utils.getString(
                        scanner,
                        "\nEnter source city: "
                );

        String destination =
                Utils.getString(
                        scanner,
                        "Enter destination city: "
                );

        graphEngine.shortestPath(
                source,
                destination
        );

        Utils.pause(scanner);
    }

    // =========================================================
    // ALGORITHM OVERVIEW
    // =========================================================

    private void showAlgorithmOverview() {

        Utils.printHeader(
                "ALGORITHM OVERVIEW"
        );

        System.out.println(
                "Data Ingestion  : Queue + Hashing"
        );

        System.out.println(
                "Cataloging      : Arrays"
        );

        System.out.println(
                "Search Engine   : BST + Heap"
        );

        System.out.println(
                "Optimizer       : Greedy + Dynamic Programming"
        );

        System.out.println(
                "Visualization   : Graphs"
        );

        System.out.println();

        System.out.println(
                "These algorithms work together to process,"
        );

        System.out.println(
                "search, optimize and analyze business data."
        );

        Utils.pause(scanner);
    }

    // =========================================================
    // COMPLEXITY OVERVIEW
    // =========================================================

    private void showComplexityReport() {

        Utils.printHeader(
                "ALGORITHMIC COMPLEXITY"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Algorithm",
                "Complexity"
        );

        Utils.printSeparator();

        System.out.printf(
                "%-25s %-15s%n",
                "Queue Enqueue",
                "O(1)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Queue Dequeue",
                "O(1)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Hash Lookup",
                "O(1) average"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Array Access",
                "O(1)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "BST Search",
                "O(log n) average"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Heap Insert",
                "O(log n)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Heap Extract",
                "O(log n)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Greedy",
                "O(n log n)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Dynamic Programming",
                "O(nW)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "BFS / DFS",
                "O(V + E)"
        );

        System.out.printf(
                "%-25s %-15s%n",
                "Dijkstra",
                "O(E log V)"
        );

        Utils.pause(scanner);
    }
}