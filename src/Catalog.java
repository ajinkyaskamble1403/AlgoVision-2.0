public class Catalog {

    private String[][] records;
    private int size;

    public Catalog(int capacity) {
        records = new String[capacity][];
        size = 0;
    }

    // Add a record to the catalog
    public boolean addRecord(String[] record) {

        if (size >= records.length) {
            System.out.println("Catalog is full. Cannot add more records.");
            return false;
        }

        records[size] = record;
        size++;

        return true;
    }

    // Remove a record using its ID
    public boolean removeRecord(String id) {

        for (int i = 0; i < size; i++) {

            if (records[i][0].equalsIgnoreCase(id)) {

                for (int j = i; j < size - 1; j++) {
                    records[j] = records[j + 1];
                }

                records[size - 1] = null;
                size--;

                return true;
            }
        }

        return false;
    }

    // Search catalog using linear array search
    public String[] searchRecord(String id) {

        for (int i = 0; i < size; i++) {

            if (records[i][0].equalsIgnoreCase(id)) {
                return records[i];
            }
        }

        return null;
    }

    // Display all catalog records
    public void displayCatalog() {

        if (size == 0) {
            System.out.println("\nCatalog is empty.");
            return;
        }

        System.out.println("\n========== CATALOG ==========");

        for (int i = 0; i < size; i++) {
            displayRecord(records[i]);
        }
    }

    // Display a single record
    private void displayRecord(String[] record) {

        for (int i = 0; i < record.length; i++) {

            System.out.print(record[i]);

            if (i < record.length - 1) {
                System.out.print(" | ");
            }
        }

        System.out.println();
    }

    // Return current number of records
    public int getSize() {
        return size;
    }

    // Return maximum catalog capacity
    public int getCapacity() {
        return records.length;
    }

    // Clear the catalog
    public void clear() {

        for (int i = 0; i < size; i++) {
            records[i] = null;
        }

        size = 0;
    }
}