import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n==== Expense Tracker ====");
                System.out.println("1. Add Expense");
                System.out.println("2. View Expenses");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Add Expense (Feature coming soon)");
                        break;
                    case 2:
                        System.out.println("View Expenses (Feature coming soon)");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }
    }
}