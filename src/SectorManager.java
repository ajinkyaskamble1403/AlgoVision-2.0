import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SectorManager {

    // =========================================================
    // E-COMMERCE ANALYTICS
    // =========================================================

    public void showEcommerceAnalytics() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("              E-COMMERCE ANALYTICS");
        System.out.println("==================================================");

        String filePath = "data/ecommerce.csv";

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            // Skip CSV header
            reader.readLine();

            long totalInventoryValue = 0;

            String highestDemandProduct = "";
            int highestStock = 0;

            System.out.println();
            System.out.println("PRODUCT CATALOG");

            System.out.println(
                    "--------------------------------------------------"
            );

            System.out.println(
                    "ID      Product          Price       Stock"
            );

            System.out.println(
                    "--------------------------------------------------"
            );

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 5) {
                    continue;
                }

                String productId = data[0];
                String productName = data[1];
                int price = Integer.parseInt(data[3]);
                int stock = Integer.parseInt(data[4]);

                System.out.printf(
                        "%-7s %-16s %-10d %-10d%n",
                        productId,
                        productName,
                        price,
                        stock
                );

                totalInventoryValue +=
                        (long) price * stock;

                if (stock > highestStock) {

                    highestStock = stock;
                    highestDemandProduct = productName;
                }
            }

            System.out.println(
                    "--------------------------------------------------"
            );

            System.out.println(
                    "\nTotal Inventory Value: INR "
                    + totalInventoryValue
            );

            System.out.println(
                    "Highest Demand Product: "
                    + highestDemandProduct
            );

            System.out.println(
                    "Available Stock: "
                    + highestStock
            );

            System.out.println(
                    "\nE-commerce analytics completed."
            );

        } catch (IOException e) {

            System.out.println(
                    "\nError reading ecommerce.csv"
            );

            System.out.println(
                    "Make sure the file exists in the data folder."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "\nInvalid numerical data found in ecommerce.csv"
            );
        }
    }

    // =========================================================
    // FINTECH ANALYTICS
    // =========================================================

    public void showFintechAnalytics() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                 FINTECH ANALYTICS");
        System.out.println("==================================================");

        String filePath = "data/fintech.csv";

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            // Skip CSV header
            reader.readLine();

            long totalTransactionVolume = 0;

            long largestTransactionAmount = 0;
            String largestTransactionId = "";

            int successfulTransactions = 0;
            int totalTransactions = 0;

            System.out.println();
            System.out.println("TRANSACTION DATA");

            System.out.println(
                    "--------------------------------------------------------------"
            );

            System.out.println(
                    "ID      FROM       TO         AMOUNT       STATUS"
            );

            System.out.println(
                    "--------------------------------------------------------------"
            );

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 5) {
                    continue;
                }

                String transactionId = data[0];
                String sourceAccount = data[1];
                String destinationAccount = data[2];

                long amount =
                        Long.parseLong(data[3]);

                String status = data[4];

                System.out.printf(
                        "%-7s %-10s %-10s INR %-7d %-10s%n",
                        transactionId,
                        sourceAccount,
                        destinationAccount,
                        amount,
                        status
                );

                totalTransactions++;

                totalTransactionVolume += amount;

                if (amount > largestTransactionAmount) {

                    largestTransactionAmount = amount;
                    largestTransactionId = transactionId;
                }

                if (status.equalsIgnoreCase("SUCCESS")) {

                    successfulTransactions++;
                }
            }

            System.out.println(
                    "--------------------------------------------------------------"
            );

            System.out.println(
                    "\nTotal Transaction Volume: INR "
                    + totalTransactionVolume
            );

            System.out.println(
                    "Largest Transaction: "
                    + largestTransactionId
                    + " -> INR "
                    + largestTransactionAmount
            );

            System.out.println(
                    "Successful Transactions: "
                    + successfulTransactions
                    + "/"
                    + totalTransactions
            );

            System.out.println(
                    "\nFintech analytics completed."
            );

        } catch (IOException e) {

            System.out.println(
                    "\nError reading fintech.csv"
            );

            System.out.println(
                    "Make sure the file exists in the data folder."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "\nInvalid numerical data found in fintech.csv"
            );
        }
    }
}