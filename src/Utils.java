import java.util.Scanner;

public class Utils {

    // =========================================================
    // DISPLAY UTILITIES
    // =========================================================

    public static void printHeader(String title) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("              " + title);
        System.out.println("==================================================");
    }

    public static void printSeparator() {

        System.out.println(
                "--------------------------------------------------"
        );
    }

    // =========================================================
    // INPUT UTILITIES
    // =========================================================

    public static int getInteger(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                return scanner.nextInt();
            }

            System.out.println(
                    "Invalid input. Please enter a number."
            );

            scanner.next();
        }
    }

    public static String getString(
            Scanner scanner,
            String message) {

        System.out.print(message);

        return scanner.next();
    }

    // =========================================================
    // PAUSE
    // =========================================================

    public static void pause(Scanner scanner) {

        System.out.println();
        System.out.println(
                "Press Enter to continue..."
        );

        scanner.nextLine();
        scanner.nextLine();
    }
}