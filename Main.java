import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        while (true) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter expense name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();

                    expenses.add(new Expense(name, amount));
                    System.out.println("Expense added!");
                    break;

                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        double total = 0;
                        System.out.println("\n--- Your Expenses ---");

                        for (Expense e : expenses) {
                            System.out.println(e.name + " : ₹" + e.amount);
                            total += e.amount;
                        }

                        System.out.println("----------------------");
                        System.out.println("Total Spending: ₹" + total);
                    }
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