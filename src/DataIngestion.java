import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DataIngestion {

    private Queue<String[]> dataQueue;
    private Map<String, String[]> dataHashMap;

    public DataIngestion() {
        dataQueue = new LinkedList<>();
        dataHashMap = new HashMap<>();
    }

    // Add a record to the ingestion queue and hash table
    public void addRecord(String id, String[] record) {

        dataQueue.offer(record);
        dataHashMap.put(id, record);

    }

    // Process and remove the next record from the queue
    public String[] processNextRecord() {

        if (dataQueue.isEmpty()) {
            return null;
        }

        return dataQueue.poll();
    }

    // Search for a record using its ID
    public String[] searchRecord(String id) {

        return dataHashMap.get(id);
    }

    // Return number of records waiting in the queue
    public int getQueueSize() {

        return dataQueue.size();
    }

    // Return total number of records stored
    public int getRecordCount() {

        return dataHashMap.size();
    }

    // Display all records currently waiting in the queue
    public void displayQueue() {

        if (dataQueue.isEmpty()) {
            System.out.println("\nNo records currently waiting in the ingestion queue.");
            return;
        }

        System.out.println("\n========== INGESTION QUEUE ==========");

        for (String[] record : dataQueue) {
            displayRecord(record);
        }
    }

    // Display a single record
    public void displayRecord(String[] record) {

        if (record == null) {
            return;
        }

        for (int i = 0; i < record.length; i++) {

            System.out.print(record[i]);

            if (i < record.length - 1) {
                System.out.print(" | ");
            }
        }

        System.out.println();
    }

    // Load records from a CSV file
    public int loadCSV(String filePath) {

        int recordsLoaded = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean header = true;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                // Skip the CSV header
                if (header) {
                    header = false;
                    continue;
                }

                String[] record = line.split(",");

                if (record.length == 0) {
                    continue;
                }

                String id = record[0].trim();

                if (!id.isEmpty()) {
                    addRecord(id, record);
                    recordsLoaded++;
                }
            }

        } catch (IOException e) {

            System.out.println("\nUnable to load CSV file.");
            System.out.println("File: " + filePath);
            System.out.println("Reason: " + e.getMessage());
        }

        return recordsLoaded;
    }

    // Search and display a record
    public void displaySearchResult(String id) {

        String[] record = searchRecord(id);

        if (record == null) {
            System.out.println("\nRecord with ID '" + id + "' was not found.");
            return;
        }

        System.out.println("\n========== SEARCH RESULT ==========");
        displayRecord(record);
    }

    // Display ingestion statistics
    public void displayStatistics() {

        System.out.println("\n========== INGESTION STATISTICS ==========");
        System.out.println("Records stored      : " + getRecordCount());
        System.out.println("Records in queue    : " + getQueueSize());
    }
}