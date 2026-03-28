import java.util.*;
import java.io.*;

public class Main {

    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        loadExpenses(expenses);

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

                    Expense e = new Expense(name, amount);
                    expenses.add(e);
                    saveExpense(e);

                    System.out.println("Expense added and saved!");
                    break;

                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        double total = 0;
                        System.out.println("\n--- Your Expenses ---");

                        for (Expense exp : expenses) {
                            System.out.println(exp.name + " : ₹" + exp.amount);
                            total += exp.amount;
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

    // Save a single expense to file
    public static void saveExpense(Expense e) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(e.name + "," + e.amount + "\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error saving file.");
        }
    }

    // Load all expenses from file
    public static void loadExpenses(ArrayList<Expense> expenses) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String name = parts[0];
                double amount = Double.parseDouble(parts[1]);

                expenses.add(new Expense(name, amount));
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
}